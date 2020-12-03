package com.chxlay.fileserver.server;

import cn.hutool.core.io.FileUtil;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;

import javax.activation.MimetypesFileTypeMap;
import java.io.File;
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.regex.Pattern;

/**
 * @author Alay
 * @date 2020-12-02 23:12
 * @project netty
 */
public class HttpFileServerHandler extends ChannelInboundHandlerAdapter {

    private static final Pattern INSECURE_URI = Pattern.compile(".*[<>&\"].*");
    private static final Pattern ALLOWED_FILE_NAME = Pattern.compile("[A-Za-z0-9][-_A-Za-z0-9\\.]*");


    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        // 启动服务的时候配置了 HttpRequestDecoder 进行解码
        if (msg instanceof HttpRequest) {
            // 解码器进行了解码
            FullHttpRequest request = (FullHttpRequest) msg;
            if (!request.decoderResult().isSuccess()) {
                // 解码未成功
                this.send(ctx, "请求出现异常", HttpResponseStatus.INTERNAL_SERVER_ERROR);
                return;
            }

            // 限定请求方式
            if (request.method() != HttpMethod.GET) {
                // 设定为限定 GET 请求
                HttpMethod method = request.method();
                this.send(ctx, "不支持的请求方式" + method.name(), HttpResponseStatus.METHOD_NOT_ALLOWED);
                return;
            }

            String uri = request.uri();
            String path = this.sanitizeUri(uri);

            if (null == path) {
                this.send(ctx, "非法请求", HttpResponseStatus.FORBIDDEN);
                return;
            }

            File file = new File(path);

            // 验证文件合法性(文件影藏，或者 文件不存在)
            if (file.isHidden() || !file.exists()) {
                this.send(ctx, "文件不存在", HttpResponseStatus.FORBIDDEN);
                return;
            }

            if (file.isDirectory()) {
                // 是一个目录，后面有 /
                this.sendListring(ctx, file);
            } else {
                // 后面没有 /, 加 / 重定向再次请求来一次
                this.redirect(ctx, uri + "/");
            }

            // 下载,设为只读模式
            RandomAccessFile randomAccessFile = new RandomAccessFile(file, "r");
            // 获取文件的大小长度
            long length = randomAccessFile.length();

            MimetypesFileTypeMap mimetypesFileTypeMap = new MimetypesFileTypeMap();

            // 文件转 byte 数组
            byte[] fileBytes = FileUtil.readBytes(file);
            ByteBuf byteBuf = Unpooled.copiedBuffer(fileBytes);
            FullHttpResponse response =
                    new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, byteBuf);

            response.headers().set(HttpHeaderNames.CONTENT_LENGTH, length);
            response.headers().set(HttpHeaderNames.CONTENT_TYPE, mimetypesFileTypeMap.getContentType(file.getPath()));
            // 保持心跳，心跳可用
            response.headers().set(HttpHeaderNames.CONNECTION, HttpHeaderValues.KEEP_ALIVE);

            // 会写数
            ctx.writeAndFlush(response).addListener(ChannelFutureListener.CLOSE);

        }
    }

    /**
     * 请求重定向
     *
     * @param ctx
     * @param newURI
     */
    private void redirect(ChannelHandlerContext ctx, String newURI) {
        FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.FOUND);
        // 重定向到连接
        response.headers().set(HttpHeaderNames.LOCATION, newURI);

        ctx.writeAndFlush(response).addListener(ChannelFutureListener.CLOSE);

    }

    /**
     * 发送消息给客户端
     *
     * @param ctx
     * @param responseMsg
     * @param status
     */
    private void send(ChannelHandlerContext ctx, String responseMsg, HttpResponseStatus status) {
        ByteBuf byteBuf = Unpooled.copiedBuffer(responseMsg, CharsetUtil.UTF_8);
        // 向客户端响应数据会写
        FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, status, byteBuf);

        HttpHeaders headers = response.headers();
        headers.set(HttpHeaderNames.CONTENT_TYPE, "text/plain:charset=UTF-8");

        // 发送响应数据
        ChannelFuture channelFuture = ctx.writeAndFlush(response);
        // 发送数据结束后关闭通道
        channelFuture.addListener(ChannelFutureListener.CLOSE);
    }


    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        ctx.close();
    }


    /**
     * 验证 URI 的合法性
     *
     * @param uri
     * @return
     * @throws Exception
     */
    private String sanitizeUri(String uri) throws UnsupportedEncodingException {
        try {
            uri = URLDecoder.decode(uri, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            try {
                uri = URLDecoder.decode(uri, "ISO-8859-1");
            } catch (UnsupportedEncodingException exception) {
                throw exception;
            }
        }

        if (!uri.startsWith("/")) {
            return null;
        }

        // 处理不同的操作系统 / \ 斜杠不一致的问题
        uri = uri.replace('/', File.separatorChar);

        // 请求 URI 合法性的校验
        if (uri.contains(File.pathSeparator + '.')
                || uri.contains('.' + File.pathSeparator)
                || uri.startsWith(".")
                || uri.endsWith(".")
                || INSECURE_URI.matcher(uri).matches()) {
            return null;
        }

        // 返回系统更目录地址 +  解码后的 URI
        return System.getProperty("user.dir") + uri;
    }


    /**
     * 对页面的渲染
     *
     * @param ctx
     * @param dir
     */
    private void sendListring(ChannelHandlerContext ctx, File dir) {
        // 向客户端响应数据会写
        FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK);
        response.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/plain:charset=UTF-8");

        String path = dir.getPath();

        StringBuilder sb = new StringBuilder();
        sb.append("<!DOCTYPE html>\r\n")
                .append("<html>\r\n<head><title>")
                .append(path)
                .append("</title></head>\r\n<body>\r\n")

                .append("<h3>")
                .append(path).append("目录")
                .append("</h3>\r\n")
                .append("<ul>\r\n")
                .append("<li>连接:<a href=\"../\")..</a></li>\r\n");

        for (File file : dir.listFiles()) {
            if (file.isHidden() || !file.canRead()) {
                // 跳过影藏文件和只读文件
                continue;
            }

            String fileName = file.getName();
            if (!ALLOWED_FILE_NAME.matcher(fileName).matches()) {
                continue;
            }

            sb.append("<li>链接: <a href=\"")
                    .append(fileName)
                    .append("\">")
                    .append(fileName)
                    .append("</a></li>\r\n");
        }

        sb.append("</ul>\r\n</body>\r\n</html>\r\n");

        ByteBuf byteBuf = Unpooled.copiedBuffer(sb, CharsetUtil.UTF_8);
        response.content().writeBytes(byteBuf);
        byteBuf.release();
        ctx.writeAndFlush(response).addListener(ChannelFutureListener.CLOSE);
    }

}
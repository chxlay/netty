package com.chxlay.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Alay
 * @date 2020-12-15 22:26
 * @project netty
 */
public class NioServer {
    /**
     * 用于维护客户端连接的
     */
    private static final ConcurrentHashMap<String, SocketChannel> CLIENT_MAP = new ConcurrentHashMap<>();

    public static void main(String[] args) throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        // 配置为非阻塞的
        serverSocketChannel.configureBlocking(false);
        // 获取到服务端所对应的Socket 对象
        ServerSocket socket = serverSocketChannel.socket();
        // 绑定地址
        InetSocketAddress socketAddress = new InetSocketAddress(10086);
        socket.bind(socketAddress);

        // 在服务器端创建一个选择器对象
        Selector selector = Selector.open();
        // 将 ServerSocketChannel 对象注册到 Selector 选择器中,选择器关注服务端的事件为 接收客户端连接事件 OP_ACCEPT
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        // 事件处理
        while (true) {
            // select() 是一个阻塞的,返回值是 选择器锁关注的事件的数量
            int select = selector.select();

            // 阻塞结束后,往下执行就能获取到 所监听的事件的 Selection 的 Key 的集合
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeys.iterator();

            while (iterator.hasNext()) {
                SelectionKey selectionKey = iterator.next();

                SocketChannel client;

                // 如果 isAcceptable() 为真，表示客户端向服务器端发起了一个连接,OP_ACCEPT 事件监听到的
                if (selectionKey.isAcceptable()) {
                    /*
                    发起连接之后可以通过当前的 selectionKey 获取到与之关联的 Channel 通道对象,然后开始调用服务器
                    端的 accept 方法，后将 服务端与 客户端连接的 Channel 对象注册到 Selector 中
                    因为在 上面的 register()，方法中注册的是 ServerSocketChannel 对象
                    所以返回的对象也一定是 ServerSocketChannel 的对象，可以直接进行强转
                     */
                    ServerSocketChannel serverChannel = (ServerSocketChannel) selectionKey.channel();

                    // 接收真正连接的客户端(到此 serverChannel 的作用已经用完了)
                    client = serverChannel.accept();
                    // 注册客户端如选择器 Selector 之前，将其设置为 非阻塞
                    client.configureBlocking(false);
                    // 将客户端也注册到 Selector 选择器中,连接已经建立,选择器关注客户端的事件为 读事件 OP_READ
                    client.register(selector, SelectionKey.OP_READ);

                    // 将客户端连接 Channel 存入 Map 进行维护
                    String key = UUID.randomUUID().toString();

                    CLIENT_MAP.put(key, client);
                }

                // 有已经建立好的连接有数据到来,触发到 SelectionKey.OP_READ 事件了
                if (selectionKey.isReadable()) {
                    /*
                    进行处理客户端读事件,获得客户端连接的通道,客户端注册到 Selector 中是什么类型的 Channel,
                    获取到的就是什么类型的 Channel 对象,所以可以直接强转相应的 Channel
                     */
                    client = (SocketChannel) selectionKey.channel();

                    // 准备读写数据的 Buffer
                    ByteBuffer readBuffer = ByteBuffer.allocate(512);
                    readBuffer.clear();

                    String receivedMessage;
                    // 开始读数据
                    while (true) {
                        int read = client.read(readBuffer);
                        if (read > 0) {
                            // 读到数据了,结束本次读取
                            // 数据读取完毕,打印控制台,给其他客户端推送出去（聊天室效果）
                            readBuffer.flip();

                            Charset charset = Charset.forName("utf-8");
                            char[] array = charset.decode(readBuffer).array();
                            receivedMessage = String.valueOf(array);
                            System.out.println("client:" + client + ",发送内容：" + receivedMessage);
                            break;
                        }
                    }

                    // 向所有客户端推消息
                    Set<Map.Entry<String, SocketChannel>> entries = CLIENT_MAP.entrySet();
                    for (Map.Entry<String, SocketChannel> entry : entries) {
                        // 准备Buffer 用于写数据给客户端
                        ByteBuffer writeBuffer = ByteBuffer.allocate(512);

                        SocketChannel currentChannel = entry.getValue();
                        // 先将客户端发送来的数据写入 writeBuffer
                        if (client == currentChannel) {
                            // 客户端自己
                            writeBuffer.put(("MySelf:" + receivedMessage).getBytes());
                        } else {
                            // 推送给他人
                            writeBuffer.put((entry.getKey() + ":" + receivedMessage).getBytes());
                        }
                        // 写入数据发送给客户端
                        currentChannel.write(writeBuffer);
                    }
                }
                // 处理完一个 Selection 之后移除
                iterator.remove();
            }
        }


    }
}
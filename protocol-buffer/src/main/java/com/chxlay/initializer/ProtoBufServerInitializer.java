package com.chxlay.initializer;

import com.chxlay.handler.MultiTypeDataProtoBufServerHandler;
import com.chxlay.protobuf.MultiTypeData;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;

/**
 * @author Alay
 * @date 2020-12-04 23:53
 * @project netty
 */
public class ProtoBufServerInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ch.pipeline()

                // 第一个：
                .addLast(new ProtobufVarint32FrameDecoder())

                /**
                 * 第二个（重要）：解码的是Proto文件生成的那个外部的类的实例,
                 *当传输多种类型对象数据时,一个类的示例显然无法满足，参看 MultiTypeData.proto,中解读
                 */
                //.addLast(new ProtobufDecoder(ProtoBufData.Person.getDefaultInstance()))
                .addLast(new ProtobufDecoder(MultiTypeData.ProtoData.getDefaultInstance()))

                // 第三个：
                .addLast(new ProtobufVarint32LengthFieldPrepender())

                //第四个：
                .addLast(new ProtobufEncoder())
                // 自定义的拦截器
                .addLast(new MultiTypeDataProtoBufServerHandler());
    }

}

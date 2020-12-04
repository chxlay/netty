package com.chxlay.handler;

import com.chxlay.protobuf.ProtoBufData;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * 这里的泛型正式 Proto文件所编写的内部类的类型
 * 简单测试,单一类的数据传输
 *
 * @author Alay
 * @date 2020-12-04 23:57
 * @project netty
 */
public class ProtoBufServerHandler extends SimpleChannelInboundHandler<ProtoBufData.Person> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ProtoBufData.Person msg) throws Exception {

        System.err.println("客户来的数据反系列化后："
                + "( name:" + msg.getName() + ", age:" + msg.getAge() + ",Addres:" + msg.getAddress() + " )");

        ProtoBufData.Person person = ProtoBufData.Person.newBuilder()
                .setName("服务端 channelRead0")
                .setAge(19)
                .setAddress("美国 channelRead0")
                .build();
        ctx.channel().writeAndFlush(person);
    }
}
package com.chxlay.handler;

import com.chxlay.protobuf.ProtoBufData;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * 这里的泛型正式 Proto文件所编写的内部类的类型
 * 简单测试,单一类的数据传输
 *
 * @author Alay
 * @date 2020-12-05 00:19
 * @project netty
 */
public class ProtoBufClientHandler extends SimpleChannelInboundHandler<ProtoBufData.Person> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ProtoBufData.Person msg) throws Exception {

        System.out.println("服务端传输过来的数据反系列化后："
                + "( name:" + msg.getName() + ", age:" + msg.getAge() + ",Addres:" + msg.getAddress() + " )");

        ProtoBufData.Person person = ProtoBufData.Person.newBuilder()
                .setName("客户端 channelRead0")
                .setAge(18)
                .setAddress("中国 channelRead0")
                .build();
        ctx.channel().writeAndFlush(person);
        Thread.sleep(3000);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ProtoBufData.Person person = ProtoBufData.Person.newBuilder()
                .setName("客户端 Active")
                .setAge(18)
                .setAddress("中国 Active")
                .build();
        ctx.channel().writeAndFlush(person);
    }
}

package com.chxlay.handler;

import com.chxlay.protobuf.MultiTypeData;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Random;

/**
 * 多类型 ProtoBuf 数据传输
 *
 * @author Alay
 * @date 2020-12-05 01:46
 * @project netty
 */
public class MultiTypeDataProtoBufClientHandler extends SimpleChannelInboundHandler<MultiTypeData.ProtoData> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MultiTypeData.ProtoData msg) throws Exception {

    }


    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        // 模拟三种类对象数据类型
        int nextInt = new Random().nextInt(3);
        MultiTypeData.ProtoData message = null;
        switch (nextInt) {
            case 1:
                message = MultiTypeData.ProtoData.newBuilder()
                        .setDataType(MultiTypeData.ProtoData.DataType.PersonType)
                        .setPerson(
                                MultiTypeData.Person.newBuilder()
                                        .setName("多数据之 Person")
                                        .setAge(19)
                                        .setSex("男")
                                        .setCitizenship("中国")
                                        .setAddress("云南")
                        ).build();
                break;
            case 2:
                message = MultiTypeData.ProtoData.newBuilder()
                        .setDataType(MultiTypeData.ProtoData.DataType.StudentType)
                        .setStudent(
                                MultiTypeData.Student.newBuilder()
                                        .setStuId(1)
                                        .setName("多数据之 Student")
                                        .setGrade("高一")
                        ).build();
                break;
            case 3:
                message = MultiTypeData.ProtoData.newBuilder()
                        .setDataType(MultiTypeData.ProtoData.DataType.TeacherType)
                        .setTeacher(
                                MultiTypeData.Teacher.newBuilder()
                                        .setId(2)
                                        .setSex("女")
                                        .setName("多数据之 Teacher")
                                        .setRank("高级讲师")
                                        .setSubject("英语")
                        ).build();
                break;
            default:
                break;
        }
        ctx.channel().writeAndFlush(message);
    }
}

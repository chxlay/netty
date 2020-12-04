package com.chxlay.handler;

import com.chxlay.protobuf.MultiTypeData;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * 多类型 ProtoBuf 数据传输
 *
 * @author Alay
 * @date 2020-12-05 01:46
 * @project netty
 */
public class MultiTypeDataProtoBufServerHandler extends SimpleChannelInboundHandler<MultiTypeData.ProtoData> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MultiTypeData.ProtoData msg) throws Exception {
        // 获取实际传输数据的类型
        MultiTypeData.ProtoData.DataType dataType = msg.getDataType();
        switch (dataType) {
            case PersonType:
                MultiTypeData.Person person = msg.getPerson();
                System.out.println(person.getClass().getSimpleName());
                break;
            case StudentType:
                MultiTypeData.Student student = msg.getStudent();
                System.out.println(student.getClass().getSimpleName());
                break;
            case TeacherType:
                MultiTypeData.Teacher teacher = msg.getTeacher();
                System.out.println(teacher.getClass().getSimpleName());
                break;
            default:
                break;
        }
    }
}

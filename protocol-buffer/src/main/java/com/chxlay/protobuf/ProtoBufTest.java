package com.chxlay.protobuf;

import com.google.protobuf.InvalidProtocolBufferException;

/**
 * @author Alay
 * @date 2020-12-04 23:27
 * @project netty
 */
public class ProtoBufTest {

    public static void main(String[] args) throws InvalidProtocolBufferException {
        ProtoBufData.Person person = ProtoBufData.Person.newBuilder()
                .setName("张三")
                .setAge(30)
                .setAddress("中国")
                .build();

        System.out.println("系列化前：" + person);


        // 系列换成为 Byte数组
        byte[] student2ByteArray = person.toByteArray();

        // 凡系列话成为对象
        ProtoBufData.Person toPerson = ProtoBufData.Person.parseFrom(student2ByteArray);

        System.out.println("反系列化后对象：" + toPerson);

        System.out.println(toPerson.getName());
        System.out.println(toPerson.getAge());
        System.out.println(toPerson.getAddress());

    }

}
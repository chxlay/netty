package com.chxlay.thrift;

import com.chxlay.thrift.generate.Person;
import com.chxlay.thrift.generate.PersonService;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;

/**
 * @author Alay
 * @date 2020-12-06 17:14
 * @project netty
 */
public class ThriftClient {
    public static void main(String[] args) {

        TSocket tSocket = new TSocket("127.0.0.1", 11000, 600);

        // TFramedTransport 和 服务端保持一致 arg.transportFactory(new TFramedTransport.Factory());
        TTransport transport = new TFramedTransport(tSocket);

        TCompactProtocol protocol = new TCompactProtocol(transport);

        PersonService.Client client = new PersonService.Client(protocol);

        try {
            transport.open();

            // 开始可以调用方法,使用起来像调用本地方法一样，实际是一个 TCP 远程调用
            Person person = client.getById((byte) 1);

            // 调用方法
            Person savePerson = new Person();
            savePerson.setName("客户端传的参数");
            client.save(savePerson);

            System.out.println(person);

        } catch (TException e) {
            e.printStackTrace();
        } finally {
            transport.close();
        }
    }
}

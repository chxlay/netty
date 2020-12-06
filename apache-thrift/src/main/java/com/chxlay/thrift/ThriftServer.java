package com.chxlay.thrift;

import com.chxlay.thrift.generate.PersonService;
import org.apache.thrift.TProcessorFactory;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.server.THsHaServer;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TNonblockingServerSocket;
import org.apache.thrift.transport.TTransportException;

/**
 * @author Alay
 * @date 2020-12-06 03:52
 * @project netty
 */
public class ThriftServer {

    public static void main(String[] args) throws TTransportException {

        // 创建Socket
        TNonblockingServerSocket serverSocket = new TNonblockingServerSocket(11000);
        // 高可用的 Server ,将 socket 作为参数
        THsHaServer.Args arg = new THsHaServer.Args(serverSocket);
        // 设置最小线程和最大线程
        arg.minWorkerThreads(2).maxWorkerThreads(4);

        // PersonService.Processor 的泛型即是 PersonService 的实现类，参数是其实现类的实例
        PersonServiceImpl personService = new PersonServiceImpl();
        PersonService.Processor<PersonServiceImpl> processor = new PersonService.Processor<>(personService);

        // 设置协议工厂
        arg.protocolFactory(new TCompactProtocol.Factory());
        // 传输协议和客户端必须一致
        arg.transportFactory(new TFramedTransport.Factory());
        arg.processorFactory(new TProcessorFactory(processor));

        THsHaServer server = new THsHaServer(arg);

        System.out.println("服务端已启动");
        // 启动,是一个异步非阻塞的死循环方法
        server.serve();
    }
}

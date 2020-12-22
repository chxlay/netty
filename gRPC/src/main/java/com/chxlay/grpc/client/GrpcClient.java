package com.chxlay.grpc.client;

import com.chxlay.grpc.protobuf.*;
import com.chxlay.grpc.service.impl.StudentServiceImpl;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;

import java.util.Iterator;

/**
 * @author Alay
 * @date 2020-12-08 21:58
 * @project netty
 */
public class GrpcClient {

    private static int port = 12000;

    public static void main(String[] args) throws InterruptedException {
        // 建立的是 TCP 连接
        ManagedChannel channel = ManagedChannelBuilder
                .forAddress("127.0.0.1", port)
                .usePlaintext()
                .build();

        GrpcClient grpcClient = new GrpcClient();
        // 调用服务端的测试方法一
         grpcClient.reqGetRes(channel);
        //调用测试方法二
        //grpcClient.reqGetStreamRes(channel);
        // 调用测试方法 三
        // grpcClient.streamReqGetRes(channel);
        // 调用测试服方法四
       // grpcClient.streamReqGetStreamRes(channel);
    }

    /**
     * 普通的请求响应
     *
     * @param channel
     */
    private void reqGetRes(ManagedChannel channel) {
        // blockingStub 阻塞的方式调用
        StudentServiceGrpc.StudentServiceBlockingStub blockingStub = StudentServiceGrpc.newBlockingStub(channel);

        // 构建请求参数
        ProtoRequest protoRequest = ProtoRequest.newBuilder().setStuName("金三胖").setStuClazz("朝鲜班").setStuId(10).build();
        // 执行请求
        ProtoResponse response = blockingStub.reqGetRes(protoRequest);
        // 返回结果打印展示
        System.out.println("姓名:" + response.getFirstName() + response.getLastName() + ",地址:" + response.getAddress());
    }

    /**
     * 客户端调用返回流式响应（返回的数据在迭代器里）
     *
     * @param channel
     */
    private void reqGetStreamRes(ManagedChannel channel) {
        StudentServiceGrpc.StudentServiceBlockingStub blockingStub = StudentServiceGrpc.newBlockingStub(channel);
        // 构建请求参数
        ProtoRequest protoRequest = ProtoRequest.newBuilder().setStuClazz("高一三班").build();
        Iterator<ProtoResponse> responseIterator = blockingStub.reqGetStreamRes(protoRequest);
        while (responseIterator.hasNext()) {
            ProtoResponse response = responseIterator.next();
            System.out.println("姓名:" + response.getFirstName() + response.getLastName() + ",地址:" + response.getAddress());
        }
    }

    /**
     * 客户端流式调用服务端
     *
     * @param channel
     */
    private void streamReqGetRes(ManagedChannel channel) throws InterruptedException {
        /**
         * 接收服务端的方法部分
         * 泛型是服务端返回的响应数据类型
         */
        StreamObserver<ResponseList> responseListStreamObserver = new StreamObserver<ResponseList>() {
            /**
             * 接收到服务端返回的数据（和服务端是反的）
             * @param responseList
             */
            @Override
            public void onNext(ResponseList responseList) {
                for (ProtoResponse response : responseList.getResponseList()) {
                    System.out.println("姓名:" + response.getFirstName() + response.getLastName() + ",地址:" + response.getAddress());
                }
            }

            /**
             * 抛出异常的时候会调用
             * @param throwable
             */
            @Override
            public void onError(Throwable throwable) {
                System.out.println("服务器端出错了：" + throwable.getMessage());
            }

            /**
             * 客户端请求完成后会调用此方法
             */
            @Override
            public void onCompleted() {
            }
        };

        /**
         * 客户端向发无端发送数据(异步发送,只要客户端是与流式发送数据,就是异步)
         * 异步的 Stub 既可以调用非流式的请求响应，又可以调用流式请求响应（支持所有类型的异步调用）
         */
        // 获得异步的
        StudentServiceGrpc.StudentServiceStub serviceStub = StudentServiceGrpc.newStub(channel);

        StreamObserver<ProtoRequest> streamObserver = serviceStub.streamReqGetRes(responseListStreamObserver);

        // 想服务端发送数据
        ProtoRequest requestZs = ProtoRequest.newBuilder().setStuId(10).setStuName("张三同学").build();
        streamObserver.onNext(requestZs);
        ProtoRequest requestLs = ProtoRequest.newBuilder().setStuId(10).setStuName("李四同学").build();
        streamObserver.onNext(requestLs);

        // 请求数据传输完毕,客户端调用结束
        streamObserver.onCompleted();

        // 异步的调用,程序执行发送数据之后,此方法调用结束了，客户端还没来得及打印接收数据的（此方法的上部分），为了看接收数据的结果，这里阻塞3秒
        Thread.sleep(3000);
    }


    /**
     * 流式请求，流式返回
     *
     * @param channel
     */
    private void streamReqGetStreamRes(ManagedChannel channel) throws InterruptedException {
        // 获得异步的
        StudentServiceGrpc.StudentServiceStub serviceStub = StudentServiceGrpc.newStub(channel);

        StreamObserver<StreamResponse> streamObserver = new StreamObserver<StreamResponse>() {
            @Override
            public void onNext(StreamResponse response) {
                User user = response.getUser();
                System.out.println("响应的数据：id=" + user.getId() + "userName=" + user.getUserName() + ",nickName=" + user.getNickName());
            }

            @Override
            public void onError(Throwable t) {
                System.out.println("客户端抛出异常");
            }

            /**
             * 客户端请求完成后会调用此方法
             */
            @Override
            public void onCompleted() {
                System.out.println("onCompleted");
            }
        };
        StreamObserver<StreamRequest> streamRequest = serviceStub.streamReqGetStreamRes(streamObserver);
        for (int i = 0; i < 10; i++) {
            // 构造请求数据
            User user = User.newBuilder().setUserName("网大葱").setNickName("大葱").setId(1).build();
            StreamRequest request = StreamRequest.newBuilder().setUser(user).buildPartial();
            streamRequest.onNext(request);

            Thread.sleep(1000);
        }
        streamRequest.onCompleted();
    }
}
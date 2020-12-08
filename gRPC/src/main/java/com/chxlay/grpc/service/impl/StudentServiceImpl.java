package com.chxlay.grpc.service.impl;

import com.chxlay.grpc.protobuf.*;
import io.grpc.stub.StreamObserver;

import java.util.Arrays;

/**
 * @author Alay
 * @date 2020-12-08 21:27
 * @project netty
 */
public class StudentServiceImpl extends StudentServiceGrpc.StudentServiceImplBase {

    /**
     * 与 thrift 不同的是，gRPC 的方法并没有返回值，返回值放在 responseObserver 中进行传输
     * 对应proto文件中的方法 ：rpc ReqGetRes(ProtoRequest) returns (ProtoResponse){}
     * thrift 的返回值和常规的Java 方法一样，返回方法的对象
     *
     * @param request
     * @param responseObserver
     */
    @Override
    public void reqGetRes(ProtoRequest request, StreamObserver<ProtoResponse> responseObserver) {
        System.out.println("客户端传过来的参数：" + request.getStuId());
        // 构建返回数据
        ProtoResponse protoResponse = ProtoResponse.newBuilder()
                .setFirstName("西门")
                .setLastName("庆")
                .setAddress("清河县")
                .build();
        // 将返回数据装入返回体
        responseObserver.onNext(protoResponse);
        // 通知客户端方法已经执行完毕
        responseObserver.onCompleted();
    }


    /**
     * 对应proto文件中的方法 ：rpc ReqGetStreamRes(ProtoRequest) returns (stream ProtoResponse){}
     * 返回流对象即可进行像集合一样的多条数据进行传输(返回的数据放入一个迭代器对象)
     *
     * @param request
     * @param responseObserver
     */
    @Override
    public void reqGetStreamRes(ProtoRequest request, StreamObserver<ProtoResponse> responseObserver) {
        System.out.println("接收到的参数" + request.getStuId());


        ProtoResponse responseZs = ProtoResponse.newBuilder()
                .setFirstName("张")
                .setLastName("三")
                .setAge(18)
                .setAddress("张三的地址")
                .build();
        responseObserver.onNext(responseZs);

        ProtoResponse responseLs = ProtoResponse.newBuilder()
                .setFirstName("李")
                .setLastName("四")
                .setAge(22)
                .setAddress("李四的地址")
                .build();
        responseObserver.onNext(responseLs);

        ProtoResponse responseWw = ProtoResponse.newBuilder()
                .setFirstName("王")
                .setLastName("五")
                .setAge(28)
                .setAddress("王五的地址")
                .build();
        responseObserver.onNext(responseWw);

        // 告诉客户端，数据传输完毕
        responseObserver.onCompleted();
    }

    /**
     * 对应proto文件中的方法 ：rpc StreamReqGetRes(stream ProtoRequest) returns (ResponseList){}
     *
     * @param responseObserver
     * @return
     */
    @Override
    public StreamObserver<ProtoRequest> streamReqGetRes(StreamObserver<ResponseList> responseObserver) {
        // 泛型是客户端请求的数据类型
        StreamObserver streamObserver = new StreamObserver<ProtoRequest>() {

            /**
             * 每次请求到了的时候都会调用此方法
             * @param value
             */
            @Override
            public void onNext(ProtoRequest value) {
                System.out.println("onNex:" + value.getStuName());
            }

            /**
             * 出错抛异常的时候会调用此方法
             * @param throwable
             */
            @Override
            public void onError(Throwable throwable) {
                System.out.println("服务器端出错了：" + throwable.getMessage());
            }

            /**
             * 客户端的流式数据全部传输完毕后,客户端调用客户端的 onCompleted(),
             * 服务端监听到 客户端调用了 onCompleted, 将会触发此方法
             */
            @Override
            public void onCompleted() {
                // 服务端响应客户端的数据
                ProtoResponse responseZs = ProtoResponse.newBuilder()
                        .setFirstName("张")
                        .setLastName("三")
                        .setAge(18)
                        .setAddress("张三的地址")
                        .build();
                ProtoResponse responseLs = ProtoResponse.newBuilder()
                        .setFirstName("李")
                        .setLastName("四")
                        .setAge(22)
                        .setAddress("李四的地址")
                        .build();

                // 返回响应
                ResponseList responseList = ResponseList.newBuilder()
                        .addResponse(responseZs)
                        .addResponse(responseLs)
                        //.addAllResponse(Arrays.asList(responseZs,responseLs))
                        .build();
                // 写入返回的响应数据
                responseObserver.onNext(responseList);
                // 服务端告诉客户端,数据完成了
                responseObserver.onCompleted();
            }
        };
        return streamObserver;
    }

    /**
     * 对应proto文件中的方法 ：rpc StreamReqGetStreamRes(stream StreamRequest) returns (stream StreamResponse){}
     *
     * @param responseObserver
     * @return
     */
    @Override
    public StreamObserver<StreamRequest> streamReqGetStreamRes(StreamObserver<StreamResponse> responseObserver) {
        StreamObserver<StreamRequest> streamObserver = new StreamObserver<StreamRequest>() {

            /**
             * 客户端每次请求都会调用此方法
             * @param request
             */
            @Override
            public void onNext(StreamRequest request) {
                User user = request.getUser();
                System.out.println("请求的数据：id=" + user.getId() + "userName=" + user.getUserName() + ",nickName=" + user.getNickName());

                // 每次请求都给客户端返回一个数据
                User resUser = User.newBuilder().setId(1).setUserName("王大锤").setNickName("大锤").build();
                StreamResponse response = StreamResponse.newBuilder().setUser(resUser).buildPartial();
                responseObserver.onNext(response);
            }

            @Override
            public void onError(Throwable throwable) {
                System.out.println("服务器端出错了：" + throwable.getMessage());
            }

            @Override
            public void onCompleted() {
                responseObserver.onCompleted();
            }
        };
        return streamObserver;
    }
}
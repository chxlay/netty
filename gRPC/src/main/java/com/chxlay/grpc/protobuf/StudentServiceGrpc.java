package com.chxlay.grpc.protobuf;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/**
 * <pre>
 * 定义RPC 的方法，通过 关键字 service 定义
 * </pre>
 */
@javax.annotation.Generated(
        value = "by gRPC proto compiler (version 1.34.0)",
        comments = "Source: Student.proto")
public final class StudentServiceGrpc {

    private StudentServiceGrpc() {
    }

    public static final String SERVICE_NAME = "com.chxlay.proto.StudentService";

    // Static method descriptors that strictly reflect the proto.
    private static volatile io.grpc.MethodDescriptor<com.chxlay.grpc.protobuf.ProtoRequest,
            com.chxlay.grpc.protobuf.ProtoResponse> getGetStuNameByStuIdMethod;

    @io.grpc.stub.annotations.RpcMethod(
            fullMethodName = SERVICE_NAME + '/' + "GetStuNameByStuId",
            requestType = com.chxlay.grpc.protobuf.ProtoRequest.class,
            responseType = com.chxlay.grpc.protobuf.ProtoResponse.class,
            methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
    public static io.grpc.MethodDescriptor<com.chxlay.grpc.protobuf.ProtoRequest,
            com.chxlay.grpc.protobuf.ProtoResponse> getGetStuNameByStuIdMethod() {
        io.grpc.MethodDescriptor<com.chxlay.grpc.protobuf.ProtoRequest, com.chxlay.grpc.protobuf.ProtoResponse> getGetStuNameByStuIdMethod;
        if ((getGetStuNameByStuIdMethod = StudentServiceGrpc.getGetStuNameByStuIdMethod) == null) {
            synchronized (StudentServiceGrpc.class) {
                if ((getGetStuNameByStuIdMethod = StudentServiceGrpc.getGetStuNameByStuIdMethod) == null) {
                    StudentServiceGrpc.getGetStuNameByStuIdMethod = getGetStuNameByStuIdMethod =
                            io.grpc.MethodDescriptor.<com.chxlay.grpc.protobuf.ProtoRequest, com.chxlay.grpc.protobuf.ProtoResponse>newBuilder()
                                    .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
                                    .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetStuNameByStuId"))
                                    .setSampledToLocalTracing(true)
                                    .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                                            com.chxlay.grpc.protobuf.ProtoRequest.getDefaultInstance()))
                                    .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                                            com.chxlay.grpc.protobuf.ProtoResponse.getDefaultInstance()))
                                    .setSchemaDescriptor(new StudentServiceMethodDescriptorSupplier("GetStuNameByStuId"))
                                    .build();
                }
            }
        }
        return getGetStuNameByStuIdMethod;
    }

    private static volatile io.grpc.MethodDescriptor<com.chxlay.grpc.protobuf.ProtoRequest,
            com.chxlay.grpc.protobuf.ProtoResponse> getGetAsStreamReqMethod;

    @io.grpc.stub.annotations.RpcMethod(
            fullMethodName = SERVICE_NAME + '/' + "GetAsStreamReq",
            requestType = com.chxlay.grpc.protobuf.ProtoRequest.class,
            responseType = com.chxlay.grpc.protobuf.ProtoResponse.class,
            methodType = io.grpc.MethodDescriptor.MethodType.CLIENT_STREAMING)
    public static io.grpc.MethodDescriptor<com.chxlay.grpc.protobuf.ProtoRequest,
            com.chxlay.grpc.protobuf.ProtoResponse> getGetAsStreamReqMethod() {
        io.grpc.MethodDescriptor<com.chxlay.grpc.protobuf.ProtoRequest, com.chxlay.grpc.protobuf.ProtoResponse> getGetAsStreamReqMethod;
        if ((getGetAsStreamReqMethod = StudentServiceGrpc.getGetAsStreamReqMethod) == null) {
            synchronized (StudentServiceGrpc.class) {
                if ((getGetAsStreamReqMethod = StudentServiceGrpc.getGetAsStreamReqMethod) == null) {
                    StudentServiceGrpc.getGetAsStreamReqMethod = getGetAsStreamReqMethod =
                            io.grpc.MethodDescriptor.<com.chxlay.grpc.protobuf.ProtoRequest, com.chxlay.grpc.protobuf.ProtoResponse>newBuilder()
                                    .setType(io.grpc.MethodDescriptor.MethodType.CLIENT_STREAMING)
                                    .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetAsStreamReq"))
                                    .setSampledToLocalTracing(true)
                                    .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                                            com.chxlay.grpc.protobuf.ProtoRequest.getDefaultInstance()))
                                    .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                                            com.chxlay.grpc.protobuf.ProtoResponse.getDefaultInstance()))
                                    .setSchemaDescriptor(new StudentServiceMethodDescriptorSupplier("GetAsStreamReq"))
                                    .build();
                }
            }
        }
        return getGetAsStreamReqMethod;
    }

    private static volatile io.grpc.MethodDescriptor<com.chxlay.grpc.protobuf.ProtoRequest,
            com.chxlay.grpc.protobuf.ProtoResponse> getGetReqReturnStreamResMethod;

    @io.grpc.stub.annotations.RpcMethod(
            fullMethodName = SERVICE_NAME + '/' + "GetReqReturnStreamRes",
            requestType = com.chxlay.grpc.protobuf.ProtoRequest.class,
            responseType = com.chxlay.grpc.protobuf.ProtoResponse.class,
            methodType = io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
    public static io.grpc.MethodDescriptor<com.chxlay.grpc.protobuf.ProtoRequest,
            com.chxlay.grpc.protobuf.ProtoResponse> getGetReqReturnStreamResMethod() {
        io.grpc.MethodDescriptor<com.chxlay.grpc.protobuf.ProtoRequest, com.chxlay.grpc.protobuf.ProtoResponse> getGetReqReturnStreamResMethod;
        if ((getGetReqReturnStreamResMethod = StudentServiceGrpc.getGetReqReturnStreamResMethod) == null) {
            synchronized (StudentServiceGrpc.class) {
                if ((getGetReqReturnStreamResMethod = StudentServiceGrpc.getGetReqReturnStreamResMethod) == null) {
                    StudentServiceGrpc.getGetReqReturnStreamResMethod = getGetReqReturnStreamResMethod =
                            io.grpc.MethodDescriptor.<com.chxlay.grpc.protobuf.ProtoRequest, com.chxlay.grpc.protobuf.ProtoResponse>newBuilder()
                                    .setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
                                    .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetReqReturnStreamRes"))
                                    .setSampledToLocalTracing(true)
                                    .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                                            com.chxlay.grpc.protobuf.ProtoRequest.getDefaultInstance()))
                                    .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                                            com.chxlay.grpc.protobuf.ProtoResponse.getDefaultInstance()))
                                    .setSchemaDescriptor(new StudentServiceMethodDescriptorSupplier("GetReqReturnStreamRes"))
                                    .build();
                }
            }
        }
        return getGetReqReturnStreamResMethod;
    }

    private static volatile io.grpc.MethodDescriptor<com.chxlay.grpc.protobuf.ProtoRequest,
            com.chxlay.grpc.protobuf.ProtoResponse> getGetBothAsStreamMethod;

    @io.grpc.stub.annotations.RpcMethod(
            fullMethodName = SERVICE_NAME + '/' + "GetBothAsStream",
            requestType = com.chxlay.grpc.protobuf.ProtoRequest.class,
            responseType = com.chxlay.grpc.protobuf.ProtoResponse.class,
            methodType = io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
    public static io.grpc.MethodDescriptor<com.chxlay.grpc.protobuf.ProtoRequest,
            com.chxlay.grpc.protobuf.ProtoResponse> getGetBothAsStreamMethod() {
        io.grpc.MethodDescriptor<com.chxlay.grpc.protobuf.ProtoRequest, com.chxlay.grpc.protobuf.ProtoResponse> getGetBothAsStreamMethod;
        if ((getGetBothAsStreamMethod = StudentServiceGrpc.getGetBothAsStreamMethod) == null) {
            synchronized (StudentServiceGrpc.class) {
                if ((getGetBothAsStreamMethod = StudentServiceGrpc.getGetBothAsStreamMethod) == null) {
                    StudentServiceGrpc.getGetBothAsStreamMethod = getGetBothAsStreamMethod =
                            io.grpc.MethodDescriptor.<com.chxlay.grpc.protobuf.ProtoRequest, com.chxlay.grpc.protobuf.ProtoResponse>newBuilder()
                                    .setType(io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
                                    .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetBothAsStream"))
                                    .setSampledToLocalTracing(true)
                                    .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                                            com.chxlay.grpc.protobuf.ProtoRequest.getDefaultInstance()))
                                    .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                                            com.chxlay.grpc.protobuf.ProtoResponse.getDefaultInstance()))
                                    .setSchemaDescriptor(new StudentServiceMethodDescriptorSupplier("GetBothAsStream"))
                                    .build();
                }
            }
        }
        return getGetBothAsStreamMethod;
    }

    /**
     * Creates a new async stub that supports all call types for the service
     */
    public static StudentServiceStub newStub(io.grpc.Channel channel) {
        io.grpc.stub.AbstractStub.StubFactory<StudentServiceStub> factory =
                new io.grpc.stub.AbstractStub.StubFactory<StudentServiceStub>() {
                    @java.lang.Override
                    public StudentServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
                        return new StudentServiceStub(channel, callOptions);
                    }
                };
        return StudentServiceStub.newStub(factory, channel);
    }

    /**
     * Creates a new blocking-style stub that supports unary and streaming output calls on the service
     */
    public static StudentServiceBlockingStub newBlockingStub(
            io.grpc.Channel channel) {
        io.grpc.stub.AbstractStub.StubFactory<StudentServiceBlockingStub> factory =
                new io.grpc.stub.AbstractStub.StubFactory<StudentServiceBlockingStub>() {
                    @java.lang.Override
                    public StudentServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
                        return new StudentServiceBlockingStub(channel, callOptions);
                    }
                };
        return StudentServiceBlockingStub.newStub(factory, channel);
    }

    /**
     * Creates a new ListenableFuture-style stub that supports unary calls on the service
     */
    public static StudentServiceFutureStub newFutureStub(
            io.grpc.Channel channel) {
        io.grpc.stub.AbstractStub.StubFactory<StudentServiceFutureStub> factory =
                new io.grpc.stub.AbstractStub.StubFactory<StudentServiceFutureStub>() {
                    @java.lang.Override
                    public StudentServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
                        return new StudentServiceFutureStub(channel, callOptions);
                    }
                };
        return StudentServiceFutureStub.newStub(factory, channel);
    }

    /**
     * <pre>
     * 定义RPC 的方法，通过 关键字 service 定义
     * </pre>
     */
    public static abstract class StudentServiceImplBase implements io.grpc.BindableService {

        /**
         * <pre>
         * 普通的的请求响应    Req: simple &lt;=========&gt;  Res : simple
         * </pre>
         */
        public void getStuNameByStuId(com.chxlay.grpc.protobuf.ProtoRequest request,
                                      io.grpc.stub.StreamObserver<com.chxlay.grpc.protobuf.ProtoResponse> responseObserver) {
            asyncUnimplementedUnaryCall(getGetStuNameByStuIdMethod(), responseObserver);
        }

        /**
         * <pre>
         *  Req: stream &lt;=========&gt; Res : simple
         * </pre>
         */
        public io.grpc.stub.StreamObserver<com.chxlay.grpc.protobuf.ProtoRequest> getAsStreamReq(
                io.grpc.stub.StreamObserver<com.chxlay.grpc.protobuf.ProtoResponse> responseObserver) {
            return asyncUnimplementedStreamingCall(getGetAsStreamReqMethod(), responseObserver);
        }

        /**
         * <pre>
         *  Req: simple &lt;=========&gt; Res : stream
         * </pre>
         */
        public void getReqReturnStreamRes(com.chxlay.grpc.protobuf.ProtoRequest request,
                                          io.grpc.stub.StreamObserver<com.chxlay.grpc.protobuf.ProtoResponse> responseObserver) {
            asyncUnimplementedUnaryCall(getGetReqReturnStreamResMethod(), responseObserver);
        }

        /**
         * <pre>
         * Req: stream &lt;=======&gt; Res : stream
         * </pre>
         */
        public io.grpc.stub.StreamObserver<com.chxlay.grpc.protobuf.ProtoRequest> getBothAsStream(
                io.grpc.stub.StreamObserver<com.chxlay.grpc.protobuf.ProtoResponse> responseObserver) {
            return asyncUnimplementedStreamingCall(getGetBothAsStreamMethod(), responseObserver);
        }

        @java.lang.Override
        public final io.grpc.ServerServiceDefinition bindService() {
            return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
                    .addMethod(
                            getGetStuNameByStuIdMethod(),
                            asyncUnaryCall(
                                    new MethodHandlers<
                                            com.chxlay.grpc.protobuf.ProtoRequest,
                                            com.chxlay.grpc.protobuf.ProtoResponse>(
                                            this, METHODID_GET_STU_NAME_BY_STU_ID)))
                    .addMethod(
                            getGetAsStreamReqMethod(),
                            asyncClientStreamingCall(
                                    new MethodHandlers<
                                            com.chxlay.grpc.protobuf.ProtoRequest,
                                            com.chxlay.grpc.protobuf.ProtoResponse>(
                                            this, METHODID_GET_AS_STREAM_REQ)))
                    .addMethod(
                            getGetReqReturnStreamResMethod(),
                            asyncServerStreamingCall(
                                    new MethodHandlers<
                                            com.chxlay.grpc.protobuf.ProtoRequest,
                                            com.chxlay.grpc.protobuf.ProtoResponse>(
                                            this, METHODID_GET_REQ_RETURN_STREAM_RES)))
                    .addMethod(
                            getGetBothAsStreamMethod(),
                            asyncBidiStreamingCall(
                                    new MethodHandlers<
                                            com.chxlay.grpc.protobuf.ProtoRequest,
                                            com.chxlay.grpc.protobuf.ProtoResponse>(
                                            this, METHODID_GET_BOTH_AS_STREAM)))
                    .build();
        }
    }

    /**
     * <pre>
     * 定义RPC 的方法，通过 关键字 service 定义
     * </pre>
     */
    public static final class StudentServiceStub extends io.grpc.stub.AbstractAsyncStub<StudentServiceStub> {
        private StudentServiceStub(
                io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
            super(channel, callOptions);
        }

        @java.lang.Override
        protected StudentServiceStub build(
                io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
            return new StudentServiceStub(channel, callOptions);
        }

        /**
         * <pre>
         * 普通的的请求响应    Req: simple &lt;=========&gt;  Res : simple
         * </pre>
         */
        public void getStuNameByStuId(com.chxlay.grpc.protobuf.ProtoRequest request,
                                      io.grpc.stub.StreamObserver<com.chxlay.grpc.protobuf.ProtoResponse> responseObserver) {
            asyncUnaryCall(
                    getChannel().newCall(getGetStuNameByStuIdMethod(), getCallOptions()), request, responseObserver);
        }

        /**
         * <pre>
         *  Req: stream &lt;=========&gt; Res : simple
         * </pre>
         */
        public io.grpc.stub.StreamObserver<com.chxlay.grpc.protobuf.ProtoRequest> getAsStreamReq(
                io.grpc.stub.StreamObserver<com.chxlay.grpc.protobuf.ProtoResponse> responseObserver) {
            return asyncClientStreamingCall(
                    getChannel().newCall(getGetAsStreamReqMethod(), getCallOptions()), responseObserver);
        }

        /**
         * <pre>
         *  Req: simple &lt;=========&gt; Res : stream
         * </pre>
         */
        public void getReqReturnStreamRes(com.chxlay.grpc.protobuf.ProtoRequest request,
                                          io.grpc.stub.StreamObserver<com.chxlay.grpc.protobuf.ProtoResponse> responseObserver) {
            asyncServerStreamingCall(
                    getChannel().newCall(getGetReqReturnStreamResMethod(), getCallOptions()), request, responseObserver);
        }

        /**
         * <pre>
         * Req: stream &lt;=======&gt; Res : stream
         * </pre>
         */
        public io.grpc.stub.StreamObserver<com.chxlay.grpc.protobuf.ProtoRequest> getBothAsStream(
                io.grpc.stub.StreamObserver<com.chxlay.grpc.protobuf.ProtoResponse> responseObserver) {
            return asyncBidiStreamingCall(
                    getChannel().newCall(getGetBothAsStreamMethod(), getCallOptions()), responseObserver);
        }
    }

    /**
     * <pre>
     * 定义RPC 的方法，通过 关键字 service 定义
     * </pre>
     */
    public static final class StudentServiceBlockingStub extends io.grpc.stub.AbstractBlockingStub<StudentServiceBlockingStub> {
        private StudentServiceBlockingStub(
                io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
            super(channel, callOptions);
        }

        @java.lang.Override
        protected StudentServiceBlockingStub build(
                io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
            return new StudentServiceBlockingStub(channel, callOptions);
        }

        /**
         * <pre>
         * 普通的的请求响应    Req: simple &lt;=========&gt;  Res : simple
         * </pre>
         */
        public com.chxlay.grpc.protobuf.ProtoResponse getStuNameByStuId(com.chxlay.grpc.protobuf.ProtoRequest request) {
            return blockingUnaryCall(
                    getChannel(), getGetStuNameByStuIdMethod(), getCallOptions(), request);
        }

        /**
         * <pre>
         *  Req: simple &lt;=========&gt; Res : stream
         * </pre>
         */
        public java.util.Iterator<com.chxlay.grpc.protobuf.ProtoResponse> getReqReturnStreamRes(
                com.chxlay.grpc.protobuf.ProtoRequest request) {
            return blockingServerStreamingCall(
                    getChannel(), getGetReqReturnStreamResMethod(), getCallOptions(), request);
        }
    }

    /**
     * <pre>
     * 定义RPC 的方法，通过 关键字 service 定义
     * </pre>
     */
    public static final class StudentServiceFutureStub extends io.grpc.stub.AbstractFutureStub<StudentServiceFutureStub> {
        private StudentServiceFutureStub(
                io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
            super(channel, callOptions);
        }

        @java.lang.Override
        protected StudentServiceFutureStub build(
                io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
            return new StudentServiceFutureStub(channel, callOptions);
        }

        /**
         * <pre>
         * 普通的的请求响应    Req: simple &lt;=========&gt;  Res : simple
         * </pre>
         */
        public com.google.common.util.concurrent.ListenableFuture<com.chxlay.grpc.protobuf.ProtoResponse> getStuNameByStuId(
                com.chxlay.grpc.protobuf.ProtoRequest request) {
            return futureUnaryCall(
                    getChannel().newCall(getGetStuNameByStuIdMethod(), getCallOptions()), request);
        }
    }

    private static final int METHODID_GET_STU_NAME_BY_STU_ID = 0;
    private static final int METHODID_GET_REQ_RETURN_STREAM_RES = 1;
    private static final int METHODID_GET_AS_STREAM_REQ = 2;
    private static final int METHODID_GET_BOTH_AS_STREAM = 3;

    private static final class MethodHandlers<Req, Resp> implements
            io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
            io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
            io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
            io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
        private final StudentServiceImplBase serviceImpl;
        private final int methodId;

        MethodHandlers(StudentServiceImplBase serviceImpl, int methodId) {
            this.serviceImpl = serviceImpl;
            this.methodId = methodId;
        }

        @java.lang.Override
        @java.lang.SuppressWarnings("unchecked")
        public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
            switch (methodId) {
                case METHODID_GET_STU_NAME_BY_STU_ID:
                    serviceImpl.getStuNameByStuId((com.chxlay.grpc.protobuf.ProtoRequest) request,
                            (io.grpc.stub.StreamObserver<com.chxlay.grpc.protobuf.ProtoResponse>) responseObserver);
                    break;
                case METHODID_GET_REQ_RETURN_STREAM_RES:
                    serviceImpl.getReqReturnStreamRes((com.chxlay.grpc.protobuf.ProtoRequest) request,
                            (io.grpc.stub.StreamObserver<com.chxlay.grpc.protobuf.ProtoResponse>) responseObserver);
                    break;
                default:
                    throw new AssertionError();
            }
        }

        @java.lang.Override
        @java.lang.SuppressWarnings("unchecked")
        public io.grpc.stub.StreamObserver<Req> invoke(
                io.grpc.stub.StreamObserver<Resp> responseObserver) {
            switch (methodId) {
                case METHODID_GET_AS_STREAM_REQ:
                    return (io.grpc.stub.StreamObserver<Req>) serviceImpl.getAsStreamReq(
                            (io.grpc.stub.StreamObserver<com.chxlay.grpc.protobuf.ProtoResponse>) responseObserver);
                case METHODID_GET_BOTH_AS_STREAM:
                    return (io.grpc.stub.StreamObserver<Req>) serviceImpl.getBothAsStream(
                            (io.grpc.stub.StreamObserver<com.chxlay.grpc.protobuf.ProtoResponse>) responseObserver);
                default:
                    throw new AssertionError();
            }
        }
    }

    private static abstract class StudentServiceBaseDescriptorSupplier
            implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
        StudentServiceBaseDescriptorSupplier() {
        }

        @java.lang.Override
        public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
            return com.chxlay.grpc.protobuf.StudentProto.getDescriptor();
        }

        @java.lang.Override
        public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
            return getFileDescriptor().findServiceByName("StudentService");
        }
    }

    private static final class StudentServiceFileDescriptorSupplier
            extends StudentServiceBaseDescriptorSupplier {
        StudentServiceFileDescriptorSupplier() {
        }
    }

    private static final class StudentServiceMethodDescriptorSupplier
            extends StudentServiceBaseDescriptorSupplier
            implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
        private final String methodName;

        StudentServiceMethodDescriptorSupplier(String methodName) {
            this.methodName = methodName;
        }

        @java.lang.Override
        public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
            return getServiceDescriptor().findMethodByName(methodName);
        }
    }

    private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

    public static io.grpc.ServiceDescriptor getServiceDescriptor() {
        io.grpc.ServiceDescriptor result = serviceDescriptor;
        if (result == null) {
            synchronized (StudentServiceGrpc.class) {
                result = serviceDescriptor;
                if (result == null) {
                    serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
                            .setSchemaDescriptor(new StudentServiceFileDescriptorSupplier())
                            .addMethod(getGetStuNameByStuIdMethod())
                            .addMethod(getGetAsStreamReqMethod())
                            .addMethod(getGetReqReturnStreamResMethod())
                            .addMethod(getGetBothAsStreamMethod())
                            .build();
                }
            }
        }
        return result;
    }
}

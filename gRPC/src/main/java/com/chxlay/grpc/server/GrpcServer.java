package com.chxlay.grpc.server;

import com.chxlay.grpc.service.impl.StudentServiceImpl;
import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * @author Alay
 * @date 2020-12-08 21:41
 * @project netty
 */
public class GrpcServer {
    private Server server;
    private final int port;

    public GrpcServer(int port) {
        this.port = port;
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        GrpcServer grpcServer = new GrpcServer(12000);
        grpcServer.start();
        grpcServer.blockUntilShutdown();
    }


    private void start() throws IOException {

        this.server = ServerBuilder
                .forPort(port)
                .addService(new StudentServiceImpl())
                .build()
                .start();
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("结束gRPC 以后结束 JVM");
            GrpcServer.this.stop();
        }));

    }

    private void stop() {
        if (this.server != null) {
            this.server.shutdown();
        }
    }

    /**
     * 等待接收信息的方法
     *
     * @throws InterruptedException
     */
    private void blockUntilShutdown() throws InterruptedException {
        if (this.server != null) {
            // this.server.awaitTermination(3000, TimeUnit.MILLISECONDS);
            this.server.awaitTermination();
        }
    }
}
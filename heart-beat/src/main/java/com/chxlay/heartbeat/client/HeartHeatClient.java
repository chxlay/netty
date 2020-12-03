package com.chxlay.heartbeat.client;

import com.chxlay.heartbeat.Initializer.ClientInitializer;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author Alay
 * @date 2020-12-04 02:50
 * @project netty
 */
public class HeartHeatClient {

    private final String hostName;
    private final int port;

    public HeartHeatClient(String hostName, int port) {
        this.hostName = hostName;
        this.port = port;
    }

    public static void main(String[] args) throws InterruptedException, IOException {
        HeartHeatClient heartHeatClient = new HeartHeatClient("localhost", 11989);
        heartHeatClient.connect();
    }


    public void connect() throws InterruptedException, IOException {
        NioEventLoopGroup eventExecutors = new NioEventLoopGroup();

        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(eventExecutors)
                    .channel(NioSocketChannel.class)
                    .handler(new ClientInitializer());

            Channel channel = bootstrap.connect(hostName, port).sync().channel();

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            while (true) {
                // 控制台输入内容
                channel.writeAndFlush(bufferedReader.readLine() + "\r\n");
                if (bufferedReader.readLine().equalsIgnoreCase("q")) {
                    break;
                }
            }
        } finally {
            eventExecutors.shutdownGracefully();
        }
    }
}
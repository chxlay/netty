package com.chxlay.zerocopy.server;

import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * @author Alay
 * @date 2020-12-30 22:00
 * @project netty
 */
public class NewIOServer {

    public static void main(String[] args) throws Exception {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        ServerSocket serverSocket = serverSocketChannel.socket();
        serverSocket.setReuseAddress(true);

        InetSocketAddress address = new InetSocketAddress(10086);
        serverSocket.bind(address);

        ByteBuffer buffer = ByteBuffer.allocate(1024);

        while (true) {
            SocketChannel socketChannel = serverSocketChannel.accept();
            socketChannel.configureBlocking(true);

            while (true) {
                int read = socketChannel.read(buffer);
                if (-1 == read) {
                    break;
                }
                buffer.rewind();
            }

        }

    }
}
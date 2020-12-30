package com.chxlay.zerocopy.server;

import java.io.DataInputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 传统IO实现
 *
 * @author Alay
 * @date 2020-12-30 21:37
 * @project netty
 */
public class OldIOServer {

    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = new ServerSocket(10086);
        // 死循环监听接收客户端
        while (true) {
            // accept() 方法是一个阻塞的方法
            Socket socket = serverSocket.accept();
            // 这里读取的是一个二进制的文件，所以需要 DataInputStream
            DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
            // 手动创建一个缓冲区
            byte[] buff = new byte[1024];

            while (true) {
                // 读取字节数的长度，当读取的长度为 -1 时，表示数据已经读取完毕
                int read = dataInputStream.read(buff, 0, buff.length);
                if (-1 == read) {
                    break;
                }
            }
        }

    }

}
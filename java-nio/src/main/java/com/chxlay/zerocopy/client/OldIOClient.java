package com.chxlay.zerocopy.client;

import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.net.Socket;

/**
 * @author Alay
 * @date 2020-12-30 22:07
 * @project netty
 */
public class OldIOClient {
    public static void main(String[] args) throws Exception {
        Socket socket = new Socket("localhost",10086);
        // 传输读取的文件路径
        String fileName = "F:/开发软件/dbeaver-ee-7.0.0-win32.win32.x86_64.zip";

        FileInputStream fileInputStream = new FileInputStream(fileName);

        // 传输二进制文件的流
        DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
        // 创建一个文件缓冲区
        byte[] buff = new byte[1024];

        long readCount;
        long total = 0;

        long startTime = System.currentTimeMillis();

        while (true) {
            readCount = fileInputStream.read(buff);
            if (-1 == readCount) {
                break;
            }
            total += readCount;
            dataOutputStream.write(buff);
        }
        System.out.println("发送总字节数：" + total + ",耗时：" + (System.currentTimeMillis() - startTime));

        dataOutputStream.close();
        socket.close();
        fileInputStream.close();
    }
}
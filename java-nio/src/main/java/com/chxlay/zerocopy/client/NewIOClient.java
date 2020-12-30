package com.chxlay.zerocopy.client;

import java.io.FileInputStream;
import java.net.InetSocketAddress;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;

/**
 * @author Alay
 * @date 2020-12-30 22:06
 * @project netty
 */
public class NewIOClient {

    public static void main(String[] args) throws Exception {
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.connect(new InetSocketAddress("localhost", 10086));
        // 配置为阻塞
        socketChannel.configureBlocking(true);

        String fileName = "F:/开发软件/Clash.for.Windows.Setup.0.13.5.exe";
        FileInputStream fileInputStream = new FileInputStream(fileName);
        FileChannel fileChannel = fileInputStream.getChannel();

        long startTime = System.currentTimeMillis();

        // transferTo() 文件从fileChannel 写到其他Channel 如： socketChannel,返回实际传递的字节数
        long transferCount = fileChannel.transferTo(0, fileChannel.size(), socketChannel);

        System.out.println("发送总字节数：" + transferCount + ",耗时：" + (System.currentTimeMillis() - startTime));

        fileChannel.close();
    }
}
package main.java.com.chxlay.test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.RandomAccessFile;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.*;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Set;

/**
 * @author Alay
 * @date 2020-12-11 21:25
 * @project netty
 */
public class NioTest {
    public static void main(String[] args) throws Exception {
        int[] ports = new int[5];
        ports[0] = 11000;
        ports[1] = 12000;
        ports[2] = 13000;
        ports[3] = 14000;
        ports[4] = 15000;

        Selector selector = Selector.open();

        for (int i = 0; i < ports.length; i++) {
            ServerSocketChannel socketChannel = ServerSocketChannel.open();
            // 配置阻塞属性，false 非阻塞
            socketChannel.configureBlocking(false);

            // 获得一个服务端连接
            ServerSocket serverSocket = socketChannel.socket();

            InetSocketAddress address = new InetSocketAddress((ports[i]));
            serverSocket.bind(address);

            // 注册通道与选择器之间的关联关系 必须是：SelectionKey.OP_ACCEPT,当客户端与服务端发起连接的时候才能获取到连接
            socketChannel.register(selector, SelectionKey.OP_ACCEPT);

            System.out.println("监听端口：" + ports[i]);
        }

        // 监听
        while (true) {
            // 返回 Selects 的 Key 的数量
            int keysNum = selector.select();
            System.out.println("keyNumbers:" + keysNum);

            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while (iterator.hasNext()) {
                SelectionKey selectionKey = iterator.next();

                if (selectionKey.isAcceptable()) {
                    // 连接客户端
                    ServerSocketChannel serverSocketChannel = (ServerSocketChannel) selectionKey.channel();

                    // 接收客户端的连接通道
                    SocketChannel socketChannel = serverSocketChannel.accept();
                    socketChannel.configureBlocking(false);

                    socketChannel.register(selector, SelectionKey.OP_READ);

                    // 接收到了当前客户端之后移除，否则重复的接收
                    iterator.remove();

                    System.out.println("获得客户端连接:" + socketChannel);
                } else if (selectionKey.isReadable()) {
                    // 读取数据
                    SocketChannel socketChannel = (SocketChannel) selectionKey.channel();

                    int bytesRead = 0;

                    while (true) {
                        ByteBuffer buffer = ByteBuffer.allocate(512);
                        buffer.clear();
                        int read = socketChannel.read(buffer);
                        if (read == -1) {
                            // 数据读取完毕
                            break;
                        }

                        // 数据读完,写数据回客户端（这里吧读取的数据写回去）
                        buffer.flip();
                        socketChannel.write(buffer);
                        bytesRead += read;
                    }
                    System.out.println("读取：" + bytesRead + ",来自于：" + socketChannel);

                    // 读取完数据之后移除，避免重复读取
                    iterator.remove();
                }
            }
        }
    }
}
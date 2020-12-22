package com.chxlay.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Alay
 * @date 2020-12-16 00:35
 * @project netty
 */
public class NioClient {

    public static void main(String[] args) throws IOException {
        // 打开客户端 Channel
        SocketChannel socketChannel = SocketChannel.open();
        // 配置为非阻塞
        socketChannel.configureBlocking(false);
        // 获得选择器对象
        Selector selector = Selector.open();
        // 注册客户端到选择器,监听的是连接事件 OP_CONNECT
        socketChannel.register(selector, SelectionKey.OP_CONNECT);
        // 建立连接
        InetSocketAddress address = new InetSocketAddress("127.0.0.1", 10086);
        socketChannel.connect(address);

        while (true) {
            // 阻塞监听服务端的返回
            int select = selector.select();
            // 一但服务端返回数据,往下执行,就能获得 Selection中的相关
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while (iterator.hasNext()) {
                SelectionKey selectionKey = iterator.next();

                // 与服务器端是否已经建立好连接了
                if (selectionKey.isConnectable()) {
                    SocketChannel clientChannel = (SocketChannel) selectionKey.channel();
                    // 连接是否属于一个挂起的状态,连接正在进行的状态
                    if (clientChannel.isConnectionPending()) {
                        // 完成这个连接,才真正的完成了连接
                        boolean finishConnect = clientChannel.finishConnect();

                        // 写数据给 服务端
                        ByteBuffer writBuffer = ByteBuffer.allocate(512);
                        // 先将要发送的数据写入 Buffer (对于 Buffer来说是一个读的操作 put)
                        writBuffer.put((LocalDateTime.now() + "连接成功").getBytes());
                        // 读取数据前进行翻转,转换读写状态
                        writBuffer.flip();
                        // 数据发送到服务器端
                        clientChannel.write(writBuffer);

                        // 单开一个线程进行监听读取客户单的数据（可以自行写多线程类实现）这里使用线程池临时使用
                        ExecutorService executor = Executors.newSingleThreadExecutor(Executors.defaultThreadFactory());
                        executor.submit(() -> {
                            // 以键盘流的形式进行传输,所以死循环监听键盘输入
                            while (true) {
                                writBuffer.clear();
                                InputStreamReader inputStreamReader = new InputStreamReader(System.in);
                                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                                String message = bufferedReader.readLine();

                                // 键盘输入的数据读入 Buffer,然后翻转，然后写给服务端
                                writBuffer.put(message.getBytes());
                                writBuffer.flip();
                                clientChannel.write(writBuffer);
                            }
                        });
                    }
                    // 连接成功之后,注册客户端读取服务端的数据的事件
                    clientChannel.register(selector, SelectionKey.OP_READ);
                }

                // 监听到到读事件
                if (selectionKey.isReadable()) {
                    SocketChannel clientChannel = (SocketChannel) selectionKey.channel();

                    // 读取服务器端发送来的数据
                    ByteBuffer readBuffer = ByteBuffer.allocate(512);
                    readBuffer.clear();

                    int read = clientChannel.read(readBuffer);
                    if (read > 0) {
                        // 读取数据有效
                        readBuffer.flip();
                        Charset charset = Charset.forName("utf-8");
                        char[] array = charset.decode(readBuffer).array();
                        String message = String.valueOf(array);
                        System.out.println("服务端发送来的消息：" + message);
                    }
                }

                // 清除已经处理完的 SelectionKey
                iterator.remove();
            }
        }
    }
}
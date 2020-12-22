package com.chxlay.test;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;

/**
 * @author Alay
 * @date 2020-12-22 14:03
 * @project netty
 */
public class CharsetTest {

    public static void main(String[] args) throws IOException {
        String inPutPath = "java-nio/src/main/resource/CharsetTest.txt";
        String outPutPath = "java-nio/src/main/resource/OutCharsetTest.txt";

        RandomAccessFile inFile = new RandomAccessFile(inPutPath, "r");
        RandomAccessFile ourFile = new RandomAccessFile(outPutPath, "rw");

        File file = new File(inPutPath);
        long fileLength = file.length();


        FileChannel inChannel = inFile.getChannel();
        FileChannel outChannel = ourFile.getChannel();

        MappedByteBuffer inputData = inChannel.map(FileChannel.MapMode.READ_ONLY, 0, fileLength);

        System.err.println("-------------- 查看计系统所支持的字符集 ---------------------");
        Charset.availableCharsets().forEach((k, v) -> {
            System.err.println(k + "," + v);
        });

        Charset charset = Charset.forName("utf-8");
        CharsetDecoder decoder = charset.newDecoder();
        CharsetEncoder encoder = charset.newEncoder();

        // 进行编解码
        CharBuffer inCharBuffer = decoder.decode(inputData);
        ByteBuffer outByteBuffer = encoder.encode(inCharBuffer);

        // 写入输出数据
        outChannel.write(outByteBuffer);

        inFile.close();
        ourFile.close();
    }
}
package com.chxlay;

import com.chxlay.common.exception.MyException;
import com.chxlay.config.SpringBeanUtil;
import com.chxlay.server.AbsSocketServer;
import com.chxlay.server.SocketServer;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * Socket 模块
 * 请不要启动运行此项目,此项目是从开发中移植过来的,并不具备环境及配置,只是单纯的为了不报错而添加了一些类
 *
 * @author Alay
 * @date 2020-12-21 11:48
 * @project Braineex
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class MySocketApplication implements CommandLineRunner {

    public static void main(String[] args) throws MyException {
        throw new MyException("ERR_002", "请不要启动运行此项目,此项目是从开发中移植过来的,并不具备环境及配置,只是单纯的为了不报错而添加了一些类");
        /* SpringApplication.run(MySocketApplication.class, args);*/
    }

    @Override
    public void run(String... args) throws InterruptedException {
        this.startNetty();
    }

    private void startNetty() throws InterruptedException {
        AbsSocketServer socketServer = SpringBeanUtil.getBean(SocketServer.class);
        socketServer.start();
        socketServer.registerNacos();
    }
}
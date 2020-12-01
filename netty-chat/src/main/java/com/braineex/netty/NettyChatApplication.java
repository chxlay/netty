package com.braineex.netty;

import com.braineex.netty.chat.config.ChatServerApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author Alay
 * @date 2020-12-01 13:55
 * @project netty-chat
 */
@SpringBootApplication
public class NettyChatApplication {

    public static void main(String[] args) throws InterruptedException {
        ConfigurableApplicationContext context = SpringApplication.run(NettyChatApplication.class, args);
        ChatServerApplication chatServerApplication = context.getBean(ChatServerApplication.class);
        chatServerApplication.start();
    }

}

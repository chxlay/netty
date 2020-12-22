package com.chxlay.config;

import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.SelfSignedCertificate;
import lombok.SneakyThrows;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLEngine;
import java.io.FileInputStream;
import java.security.KeyStore;

/**
 * SSL 签名秘钥拦截器
 * 证书生成命令为：
 * keytool -genkey -keysize 2048 -validity 365 -keyalg RSA -keypass braineex -storepass braineex -keystore E:\braineex_wss.jks
 *
 * @author Alay
 * @date 2020-12-18 14:38
 * @project Braineex
 */
@Configuration
public class SslConfig {

	private final String password = "braineex";
	private final String TYPE_JKS = "JKS";
	private final String TYPE_TLS = "TLS";

	/**
	 * netty提供的证书
	 *
	 * @return
	 */
	@Bean
	@SneakyThrows
	public SslContext nettySslCtx() {
		SelfSignedCertificate certificate = new SelfSignedCertificate();
		SslContext sslContext = SslContextBuilder.forServer(certificate.certificate(), certificate.privateKey()).build();
		return sslContext;
	}


	@Bean
	public SSLEngine sslEngine() {
		SSLContext sslContext = this.jdkSslCtx();
		SSLEngine sslEngine = sslContext.createSSLEngine();
		sslEngine.setUseClientMode(false);
		sslEngine.setNeedClientAuth(false);
		return sslEngine;
	}


	/**
	 * 自定义证书上下文对象
	 *
	 * @return
	 */
	@SneakyThrows
	public SSLContext jdkSslCtx() {
		KeyStore keyStore = KeyStore.getInstance(TYPE_JKS);
		// 证书存放地址
		FileInputStream jksFile = new FileInputStream("../../../../../../resources/ssl/braineex_wss.jks");
		keyStore.load(jksFile, password.toCharArray());

		// KeyManagerFactory充当基于密钥内容源的密钥管理器的工厂
		KeyManagerFactory factoryInstance = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
		factoryInstance.init(keyStore, password.toCharArray());

		// SSLContext的实例表示安全套接字协议的实现，它充当用于安全套接字工厂或 SSLEngine 的工厂
		SSLContext sslContext = SSLContext.getInstance(TYPE_TLS);
		sslContext.init(factoryInstance.getKeyManagers(), null, null);
		return sslContext;
	}

}
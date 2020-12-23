package com.chxlay.gateway.filter;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @author Alay
 * @date 2020-12-23 14:57
 * @project netty
 */
@Component
public class GlobalLogFilter implements GlobalFilter, Ordered {

    /**
     * 实现自定义过滤器的逻辑
     *
     * @param exchange 请求的上下文对象
     * @param chain    通道
     * @return
     */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        System.out.println("进入全局过滤器");
        ServerHttpRequest request = exchange.getRequest();
        String username = request.getQueryParams().getFirst("username");
        if (null == username) {
            System.out.println("请求参数中没有 username,不允许访问");
            exchange.getResponse().setStatusCode(HttpStatus.NOT_ACCEPTABLE);
            return exchange.getResponse().setComplete();
        }
        // 传递给下一个过滤链
        return chain.filter(exchange);
    }

    /**
     * 加载过滤器的顺序，数字越小，优先级越高
     * 最小数：int HIGHEST_PRECEDENCE = -2147483648;
     * 最大数：int LOWEST_PRECEDENCE = 2147483647;
     *
     * @return
     */
    @Override
    public int getOrder() {
        return -1000;
    }
}
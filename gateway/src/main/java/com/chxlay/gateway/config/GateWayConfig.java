package com.chxlay.gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;

/**
 * @author Alay
 * @date 2020-12-23 12:08
 * @project netty
 */
//@Configuration
public class GateWayConfig {

//    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder routeBuilder) {
        RouteLocatorBuilder.Builder routes = routeBuilder.routes();
        /*
         * 配置路由映射 https://news.baidu.com/guonei,即访问本地的 localhost:9527/guonei,
         * 将会转发到 https://news.baidu.com/guonei
         */
        routes.route("routeId", r -> r.path("/guonei").uri("http://news.baidu.com/guonei"));

        return routes.build();
    }
}
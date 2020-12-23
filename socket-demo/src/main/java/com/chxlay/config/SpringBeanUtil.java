package com.chxlay.config;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @author Alay
 * @date 2020-12-23 17:42
 * @project Braineex
 */
@Component
public class SpringBeanUtil implements ApplicationContextAware {

    private static ApplicationContext context;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringBeanUtil.context = applicationContext;
    }

    /**
     * 通过 名称获取实例
     *
     * @param beanName
     * @return
     * @throws BeansException
     */
    public static Object getBeanByName(String beanName) throws BeansException {
        return context.getBean(beanName);
    }

    /**
     * 通过类获取实例
     *
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T getBean(Class<T> clazz) {
        return context.getBean(clazz);
    }
}
package com.coupang.c4.step14.beanfactory;

/**
 * Created by coupang on 14. 12. 15..
 */
public interface BeanFactory extends BeanRegistry {
    <T> T getInstance(Class<T> type);
    Object getInstance(String beanName);
}

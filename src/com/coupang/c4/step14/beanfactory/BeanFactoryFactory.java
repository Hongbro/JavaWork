package com.coupang.c4.step14.beanfactory;

/**
 * Created by coupang on 14. 12. 15..
 */
public interface BeanFactoryFactory {
    BeanFactory createBeanFactory(Scope scope);
}

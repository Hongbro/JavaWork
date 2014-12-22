package com.coupang.c4.step14.beanfactory;

/**
 * Created by coupang on 14. 12. 15..
 */
public class BeanFactoryFactoryImpl implements BeanFactoryFactory {
    @Override
    public BeanFactory createBeanFactory(Scope scope){
        if(scope.equals(Scope.SINGLETON)){
            return new SingletonBeanFactory();
        }
        return null;
    }
}

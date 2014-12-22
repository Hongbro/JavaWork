package com.coupang.c4.step14.beanfactory;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by coupang on 14. 12. 15..
 */
public class SingletonBeanFactory extends ScopedBeanFactory {
    private List<BeanDefinition> beanDefinitions = new ArrayList<BeanDefinition>();
    private Map<String, Object> beans = new HashMap<String, Object>();

    public SingletonBeanFactory() {}

    @Override
    public <T> T getInstance(Class<T> type){
        for(BeanDefinition beanDefinition : beanDefinitions){
            if( beanDefinition.getClassFullName().equals(type.getName()) ){
                return (T) beans.get(beanDefinition.getBeanName());
            }
        }
        return null;
    }

    @Override
    public Object getInstance(String beanName){
        return beans.get(beanName);
    }

    @Override
    public void addNewBean(BeanDefinition beanDefinition) {
        Object instance = null;
        try {
            beanDefinitions.add(beanDefinition);
            Constructor<?> constructor = Class.forName(beanDefinition.getClassFullName()).getDeclaredConstructor();
            constructor.setAccessible(true);
            instance = constructor.newInstance();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e){
            e.printStackTrace();
        }
        beans.put(beanDefinition.getBeanName(), instance);
    }
}

package com.coupang.c4.step14.beanfactory;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.Map.Entry;
import java.util.HashMap;
import com.coupang.c4.ResourceUtil;

/**
 * 1. singleton instance 관리 - 생성된 bean 캐싱
 *       interface
 * 2. 고려 내용 추후 다른 scope 생성이 용이한 구조가 되도록
 *       map : key를 클래스
 *       singleton을 제공해주는 용도의 클래스 구조
 * 전제조건 :
 *  - 기본생성자가 있는 bean만 취급한다.
 *
 * 유연성, 확장성을 잘 고려해서 SimpleBeanFactory를 잘 나누고 추상화
 */

public class ScopedBeanFactory implements BeanFactory {
    private BeanFactoryFactory beanFactoryFactory = new BeanFactoryFactoryImpl();
    private Map<Scope, BeanFactory> beanFactories = new HashMap<Scope, BeanFactory>();

    public ScopedBeanFactory() {}

    public ScopedBeanFactory(String propertyPath, String... otherPropertyPathes){
        PropertyFileBeanDefinitionLoader propertyFileBeanDefinitionLoader = new PropertyFileBeanDefinitionLoader(propertyPath);
        BeanDefinition[] beanDefinitions = propertyFileBeanDefinitionLoader.loadBeans();
        for(BeanDefinition bean : beanDefinitions){
            this.addNewBean(bean);
        }
    }

    @Override
    public <T> T getInstance(Class<T> type) {
        for (Entry<Scope, BeanFactory> beanFactory : beanFactories.entrySet() ){
            T instance = beanFactory.getValue().getInstance(type);
            if(instance!=null){
                return instance;
            }
        }
        return null;
    }

    @Override
    public Object getInstance(String beanName) {
        for (Entry<Scope, BeanFactory> beanFactory : beanFactories.entrySet()) {
            Object instance = beanFactory.getValue().getInstance(beanName);
            if (instance != null) {
                return instance;
            }
        }
        return null;
    }

    @Override
    public void addNewBean(BeanDefinition beanDefinition) {
        if(!this.beanFactories.containsKey(beanDefinition.getScope())){
            this.beanFactories.put(beanDefinition.getScope(), this.beanFactoryFactory.createBeanFactory(beanDefinition.getScope()));
            // 기본 Scope는 SINGLETON
        }

        this.beanFactories.get(beanDefinition.getScope()).addNewBean(beanDefinition);
    }
}

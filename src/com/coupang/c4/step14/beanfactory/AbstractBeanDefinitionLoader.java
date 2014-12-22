package com.coupang.c4.step14.beanfactory;

import com.coupang.c4.ResourceUtil;

import java.io.*;

/**
 * Created by coupang on 14. 12. 11..
 */
public abstract class AbstractBeanDefinitionLoader implements BeanDefinitionLoader {

    private String configurationPath;

    protected AbstractBeanDefinitionLoader(String configurationPath){
        this.configurationPath = configurationPath;
    }

    @Override
    public BeanDefinition[] loadBeans() {
        try(InputStream inputStream = ResourceUtil.resourceAsInputStream(this.configurationPath)){
            return loadBeans(inputStream);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    abstract BeanDefinition[] loadBeans(InputStream inputStream) throws IOException;
}

package com.coupang.c4.step14.beanfactory;

import com.sun.javafx.fxml.PropertyName;
import com.sun.xml.internal.ws.api.PropertySet;

/**
 * Created by coupang on 14. 12. 11..
 */
public class BeanDefinition {
    private String beanName;
    private String classFullName;
    private Scope scope;

    public BeanDefinition(String beanName, String classFullName) {
        this.beanName = beanName;
        this.classFullName = classFullName;
        this.scope = Scope.SINGLETON;           // 디폴트 값
    }

    public Scope getScope() { return scope; }

    public void setScope(Scope scope) { this.scope = scope; }

    public String getBeanName() { return beanName; }

    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }

    public String getClassFullName() { return classFullName; }

    public void setClassFullName(String classFullName) { this.classFullName = classFullName; }

}

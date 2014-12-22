package com.coupang.c4.step14;

import com.coupang.c4.step14.beanfactory.ScopedBeanFactory;
import com.coupang.c4.step14.beans.Sample1;
import com.coupang.c4.step14.beans.Sample2;



public class Main {
	public static void main(String[] args) {
		ScopedBeanFactory scopedBeanFactory = new ScopedBeanFactory("/com/coupang/c4/step14/bean-definitions.properties");

		Sample1 instance = scopedBeanFactory.getInstance(Sample1.class);
		System.out.println(instance != null);
		
		Object instance2 = scopedBeanFactory.getInstance("sample2");
		System.out.println(instance2 != null);
		System.out.println(Sample2.class.isAssignableFrom(instance2.getClass()));
		System.out.println(instance instanceof Sample1);
	}
}

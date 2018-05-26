package com.sms.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class ApplicationContextProvider implements ApplicationContextAware {

	public static ApplicationContext context;

	private ApplicationContextProvider() {
	}

	public static ApplicationContext getApplicationContext() {
		return context;
	}

	public static <T> T getBean(String name, Class<T> aClass) {
		return context.getBean(name, aClass);
	}

	public static Object getBean(String name) {
		return context.getBean(name);
	}

	public static <T> T getBean(Class<T> aClass) {
		return context.getBean(aClass);
	}

	public void setApplicationContext(ApplicationContext ctx) throws BeansException {
		context = ctx;
	}
}
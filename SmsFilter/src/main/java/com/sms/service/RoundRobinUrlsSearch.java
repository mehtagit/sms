package com.sms.service;

import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sms.util.AppConfig;

@Component("urlsSearch")
public class RoundRobinUrlsSearch implements UrlsSearch {

	@Autowired
	private AppConfig appConfig;

	private AtomicInteger index = new AtomicInteger(0);

	public String getUrl() {
		return get();
	}

	public String get() {
		if (index.get() > appConfig.getUrls().size()) {
			index.set(0);
		}
		return appConfig.getUrls().get(index.getAndIncrement());
	}
}

package com.sms.service;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("urls")
public class RoundRobinUrls implements Urls {

	@Autowired
	@Resource(name = "urlsList")
	private List<String> urlsList;

	private AtomicInteger index = new AtomicInteger(0);

	public String getUrl() {
		return get();
	}

	public String get() {

		if (index.get() > urlsList.size()) {
			index.set(0);
		}

		return urlsList.get(index.getAndIncrement());
	}
}

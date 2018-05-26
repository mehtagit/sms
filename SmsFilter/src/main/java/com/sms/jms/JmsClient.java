package com.sms.jms;

import com.sms.bean.Request;

public interface JmsClient {
	public void send(Request request);

	public Request receive();

	public void start();

	public void stop();
}

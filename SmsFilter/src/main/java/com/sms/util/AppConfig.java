package com.sms.util;

import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;

import javax.jms.Queue;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;

@Configuration
public class AppConfig {

	public static final String HELLO_QUEUE = "hello.queue";

	@Bean
	public Queue helloJMSQueue() {
		return new ActiveMQQueue(HELLO_QUEUE);
	}

	@Bean
	public List<String> urlsList() {
		List<String> urlsList = new ArrayList<String>();
		urlsList.add("http://127.0.0.0/receivesms.php?from=<from>&to=<to>&text=<text>");
		urlsList.add("http://127.0.0.1/receivesms.php?from=<from>&to=<to>&text=<text>");
		urlsList.add("http://127.0.0.2/receivesms.php?from=<from>&to=<to>&text=<text>");
		urlsList.add("http://127.0.0.3/receivesms.php?from=<from>&to=<to>&text=<text>");
		urlsList.add("http://127.0.0.4/receivesms.php?from=<from>&to=<to>&text=<text>");
		return urlsList;
	}

	@Bean
	public DatagramSocket datagramSocket() throws SocketException {
		return new DatagramSocket();
	}

	@Bean
	public MessageConverter jacksonJmsMessageConverter() {
		MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
		converter.setTargetType(MessageType.TEXT);
		converter.setTypeIdPropertyName("_type");
		return converter;
	}
}

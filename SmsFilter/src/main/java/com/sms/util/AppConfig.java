package com.sms.util;

import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;

import com.sms.client.ClientService;

@Configuration
@ConfigurationProperties(prefix = "app")
public class AppConfig {

	@NotNull
	private boolean activateConsumer;

	@NotNull
	private String jmsQueue;

	@NotNull
	private List<String> urls = new ArrayList<String>();

	public List<String> getUrls() {
		return urls;
	}

	public void setUrls(List<String> urls) {
		this.urls = urls;
	}

	public String getJmsQueue() {
		return jmsQueue;
	}

	public void setJmsQueue(String jmsQueue) {
		this.jmsQueue = jmsQueue;
	}

	public boolean isActivateConsumer() {
		return activateConsumer;
	}

	public void setActivateConsumer(boolean activateConsumer) {
		this.activateConsumer = activateConsumer;
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

	@Bean(name = "clientService")
	public ClientService clientService(@Value("${client.datasource.url}") String clientDbUrl,
			@Value("${client.datasource.driver-class-name}") String clientDbDriver,
			@Value("${client.datasource.username}") String clientDbUser,
			@Value("${client.datasource.password}") String clientDbPass,
			@Value("${client.datasource.max}") int clientDbMaxConn,
			@Value("${client.datasource.min}") int clientDbMinConn) {
		System.out.println("clientDbUrl" + clientDbUrl);
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName(clientDbDriver);
		dataSource.setUrl(clientDbUrl);
		dataSource.setUsername(clientDbUser);
		dataSource.setPassword(clientDbPass);
		dataSource.setMaxActive(clientDbMaxConn);
		dataSource.setInitialSize(clientDbMinConn);
		/*
		 * dataSource.setValidationQuery("select NOW()");
		 * dataSource.setTestOnBorrow(true);
		 */

		return new ClientService(dataSource);
	}

}

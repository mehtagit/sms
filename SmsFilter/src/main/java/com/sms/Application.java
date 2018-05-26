package com.sms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import com.sms.jms.JmsConsumer;
import com.sms.util.ApplicationContextProvider;

@SpringBootApplication
@EnableJpaAuditing
@EnableAutoConfiguration(exclude = { JacksonAutoConfiguration.class })
@ImportResource("classpath*:/spring/si-config.xml")
public class Application {
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
		JmsConsumer jmsConsumer = ApplicationContextProvider.getBean(JmsConsumer.class);
		jmsConsumer.start();
	}
}

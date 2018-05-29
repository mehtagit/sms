package com.sms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import com.sms.jms.JmsConsumer;
import com.sms.util.AppConfig;
import com.sms.util.ApplicationContextProvider;

@SpringBootApplication(scanBasePackages = "com.sms")
@EnableJpaAuditing
public class Application {
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);

		boolean activateConsumer = ApplicationContextProvider.getBean(AppConfig.class).isActivateConsumer();
		if (activateConsumer) {
			JmsConsumer jmsConsumer = ApplicationContextProvider.getBean(JmsConsumer.class);
			jmsConsumer.start();
		}
	}
}

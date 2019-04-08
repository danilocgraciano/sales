package com.company.orders.service.order;

import static org.junit.Assert.assertTrue;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.company.orders.builder.OrderBuilder;
import com.company.orders.entity.order.Order;
import com.company.orders.messaging.MessageType;

@RunWith(SpringRunner.class)
@ContextConfiguration
public class OrderMessageServiceTest {

	@Autowired
	OrderMessageService service;

	@TestConfiguration
	static class OrderMessageServiceTestConfigurationContext {

		String BROKER_URL = "tcp://localhost:61616";
		String BROKER_USERNAME = "admin";
		String BROKER_PASSWORD = "admin";

		@Bean
		public ActiveMQConnectionFactory connectionFactory() {
			ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
			connectionFactory.setBrokerURL(BROKER_URL);
			connectionFactory.setPassword(BROKER_USERNAME);
			connectionFactory.setUserName(BROKER_PASSWORD);
			return connectionFactory;
		}

		@Bean
		public JmsTemplate jmsTemplate() {
			JmsTemplate template = new JmsTemplate();
			template.setConnectionFactory(connectionFactory());
			template.setPubSubDomain(true);
			return template;
		}

		@Bean
		public OrderMessageService orderMessageService() {
			return new OrderMessageService();
		}

	}

	@Test
	public void send() {

		Order order = new OrderBuilder().buildDefault();
		service.send(MessageType.UPINSERTED, order);
		assertTrue(true);
	}

}

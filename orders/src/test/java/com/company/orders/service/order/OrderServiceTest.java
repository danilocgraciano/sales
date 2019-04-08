package com.company.orders.service.order;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Optional;
import java.util.UUID;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import com.company.orders.builder.OrderBuilder;
import com.company.orders.entity.order.Order;
import com.company.orders.repository.order.OrderRepository;

@RunWith(SpringRunner.class)
public class OrderServiceTest {

	@MockBean
	private OrderRepository repository;

	@Autowired
	private OrderService service;

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

	@TestConfiguration
	static class OrderServiceTestConfigurationContext {

		@Bean
		public OrderService orderService() {
			return new OrderService();
		}
	}

	@Test
	public void save() {

		Order e = new OrderBuilder().buildDefault();

		when(repository.save(e)).thenReturn(e);

		service.save(e);

		verify(repository, times(1)).save(e);

	}

	@Test
	public void update() {

		Order e = new OrderBuilder().buildDefault();
		e.setId(UUID.randomUUID().toString());

		when(repository.findById(e.getId())).thenReturn(Optional.of(e));
		when(repository.save(e)).thenReturn(e);

		service.update(e.getId(), e);

		verify(repository, times(1)).save(e);

	}

	@Test
	public void deleteById() {

		Order e = new OrderBuilder().buildDefault();
		e.setId(UUID.randomUUID().toString());

		service.deleteById(e.getId());

		verify(repository, times(1)).deleteById(e.getId());

	}

	@Test
	public void findById() {

		Order e = new OrderBuilder().buildDefault();
		e.setId(UUID.randomUUID().toString());

		when(repository.findById(e.getId())).thenReturn(Optional.of(e));

		service.findById(e.getId());

		verify(repository, times(1)).findById(e.getId());

	}

	@Test
	public void findAll() {

		Order e = new OrderBuilder().buildDefault();
		e.setId(UUID.randomUUID().toString());

		Pageable pageable = PageRequest.of(0, 20);

		when(repository.findAll(pageable)).thenReturn(new PageImpl<Order>(Arrays.asList(e)));

		service.findAll(pageable);

		verify(repository, times(1)).findAll(pageable);

	}

	@Test
	public void findAllExample() {

		Order e = new OrderBuilder().buildDefault();
		e.setId(UUID.randomUUID().toString());

		Pageable pageable = PageRequest.of(0, 20);
		Example<Order> example = Example.of(e);

		when(repository.findAll(example, pageable)).thenReturn(new PageImpl<Order>(Arrays.asList(e)));

		service.findAll(e, pageable);

		verify(repository, times(1)).findAll(example, pageable);

	}

}

package com.company.orders.repository.order;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.junit4.SpringRunner;

import com.company.orders.builder.OrderBuilder;
import com.company.orders.entity.order.Order;
import com.company.orders.repository.customer.CustomerRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class OrderRepositoryTest {

	@Autowired
	OrderRepository orderRepository;
	
	@Autowired
	CustomerRepository customerRepository;

	@Test
	public void save() {

		Order order = new OrderBuilder().buildDefault();
		customerRepository.save(order.getCustomer());
		

		orderRepository.save(order);

		assertNotNull(order.getId());
	}

	@Test
	public void update() {

		Order order = new OrderBuilder().buildDefault();
		customerRepository.save(order.getCustomer());
		
		orderRepository.save(order);

		Order aOrder = orderRepository.findById(order.getId())
				.orElseThrow(() -> new EmptyResultDataAccessException("Order not found ", 1));

		aOrder.getDeliveryAddress().setCity("other");
		orderRepository.save(aOrder);

		order = orderRepository.findById(order.getId())
				.orElseThrow(() -> new EmptyResultDataAccessException("Order not found ", 1));

		assertThat(order.getDeliveryAddress().getCity(), equalTo(aOrder.getDeliveryAddress().getCity()));
	}

	@Test
	public void delete() {

		Order order = new OrderBuilder().buildDefault();
		customerRepository.save(order.getCustomer());
		
		orderRepository.save(order);

		orderRepository.delete(order);

		assertThat(!orderRepository.findById(order.getId()).isPresent());

	}

}

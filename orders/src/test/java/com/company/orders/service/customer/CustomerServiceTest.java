package com.company.orders.service.customer;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Optional;
import java.util.UUID;

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
import org.springframework.test.context.junit4.SpringRunner;

import com.company.orders.builder.CustomerBuilder;
import com.company.orders.entity.customer.Customer;
import com.company.orders.repository.customer.CustomerRepository;

@RunWith(SpringRunner.class)
public class CustomerServiceTest {

	@MockBean
	private CustomerRepository repository;

	@Autowired
	private CustomerService service;

	@TestConfiguration
	static class CustomerServiceTestConfigurationContext {

		@Bean
		public CustomerService customerService() {
			return new CustomerService();
		}
	}

	@Test
	public void save() {

		Customer e = new CustomerBuilder().buildDefault();

		when(repository.save(e)).thenReturn(e);

		service.save(e);

		verify(repository, times(1)).save(e);

	}

	@Test
	public void update() {

		Customer e = new CustomerBuilder().buildDefault();
		e.setId(UUID.randomUUID().toString());

		when(repository.findById(e.getId())).thenReturn(Optional.of(e));
		when(repository.save(e)).thenReturn(e);

		service.update(e.getId(), e);

		verify(repository, times(1)).save(e);

	}

	@Test
	public void deleteById() {

		Customer e = new CustomerBuilder().buildDefault();
		e.setId(UUID.randomUUID().toString());

		service.deleteById(e.getId());

		verify(repository, times(1)).deleteById(e.getId());

	}

	@Test
	public void findById() {

		Customer e = new CustomerBuilder().buildDefault();
		e.setId(UUID.randomUUID().toString());

		when(repository.findById(e.getId())).thenReturn(Optional.of(e));

		service.findById(e.getId());

		verify(repository, times(1)).findById(e.getId());

	}

	@Test
	public void findAll() {

		Customer e = new CustomerBuilder().buildDefault();
		e.setId(UUID.randomUUID().toString());

		Pageable pageable = PageRequest.of(0, 20);

		when(repository.findAll(pageable)).thenReturn(new PageImpl<Customer>(Arrays.asList(e)));

		service.findAll(pageable);

		verify(repository, times(1)).findAll(pageable);

	}

	@Test
	public void findAllExample() {

		Customer e = new CustomerBuilder().buildDefault();
		e.setId(UUID.randomUUID().toString());

		Pageable pageable = PageRequest.of(0, 20);
		Example<Customer> example = Example.of(e);

		when(repository.findAll(example, pageable)).thenReturn(new PageImpl<Customer>(Arrays.asList(e)));

		service.findAll(e, pageable);

		verify(repository, times(1)).findAll(example, pageable);

	}

}

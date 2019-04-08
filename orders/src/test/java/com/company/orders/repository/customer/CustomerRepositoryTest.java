package com.company.orders.repository.customer;

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

import com.company.orders.builder.CustomerBuilder;
import com.company.orders.entity.customer.Customer;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class CustomerRepositoryTest {

	@Autowired
	CustomerRepository repository;

	@Test
	public void save() {

		Customer customer = new CustomerBuilder().buildDefault();
		repository.save(customer);

		assertNotNull(customer.getId());
	}

	@Test
	public void update() {

		Customer customer = new CustomerBuilder().buildDefault();
		repository.save(customer);

		Customer aCustomer = repository.findById(customer.getId())
				.orElseThrow(() -> new EmptyResultDataAccessException("Customer not found ", 1));

		aCustomer.getAddress().forEach((address) -> address.setCustomer(aCustomer));
		aCustomer.setEmail("teste@email.com");
		repository.save(aCustomer);

		customer = repository.findById(customer.getId())
				.orElseThrow(() -> new EmptyResultDataAccessException("Customer not found ", 1));

		assertThat(customer.getEmail(), equalTo(aCustomer.getEmail()));
	}

	@Test
	public void delete() {

		Customer customer = new CustomerBuilder().buildDefault();
		repository.save(customer);

		repository.delete(customer);

		assertThat(!repository.findById(customer.getId()).isPresent());

	}

}

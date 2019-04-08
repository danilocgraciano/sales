package com.company.orders.controller.customer;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.UUID;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.company.orders.builder.CustomerBuilder;
import com.company.orders.entity.customer.Customer;
import com.company.orders.service.customer.CustomerService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@WebMvcTest(CustomerController.class)
public class CustomerControllerTest {

	@Autowired
	protected MockMvc mvc;

	@MockBean
	private CustomerService service;

	private String uri = "/customers";

	@Test
	public void save() throws Exception {

		ObjectMapper objectMapper = new ObjectMapper();

		Customer customer = new CustomerBuilder().withId(UUID.randomUUID().toString()).buildDefault();
		when(service.save(customer)).thenReturn(customer);

		mvc.perform(post(this.uri).contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(objectMapper.writeValueAsString(customer))).andExpect(status().isCreated())
				.andExpect(header().string("Location", containsString(customer.getId())));

	}

	@Test
	public void update() throws Exception {

		ObjectMapper objectMapper = new ObjectMapper();

		Customer customer = new CustomerBuilder().withId(UUID.randomUUID().toString()).buildDefault();
		when(service.save(customer)).thenReturn(customer);

		mvc.perform(put(this.uri + "/" + customer.getId()).contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(objectMapper.writeValueAsString(customer))).andExpect(status().isOk());

	}

	@Test
	public void remove() throws Exception {

		Customer customer = new CustomerBuilder().withId(UUID.randomUUID().toString()).buildDefault();
		mvc.perform(delete(this.uri + "/" + customer.getId()).contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk());

	}

	@Test
	public void findOne() throws Exception {

		Customer customer = new CustomerBuilder().withId(UUID.randomUUID().toString()).buildDefault();

		when(service.findById(customer.getId())).thenReturn(customer);

		mvc.perform(get(this.uri + "/" + customer.getId()).contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk()).andExpect(jsonPath("$.id", equalTo(customer.getId())));
	}

	@Test
	public void findAll() throws Exception {

		Customer customer1 = new CustomerBuilder().withId(UUID.randomUUID().toString()).buildDefault();
		Customer customer2 = new CustomerBuilder().withId(UUID.randomUUID().toString()).buildDefault();

		when(service.findAll(PageRequest.of(0, 20)))
				.thenReturn(new PageImpl<Customer>(Arrays.asList(customer1, customer2)));

		mvc.perform(get(this.uri).contentType(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk())
				.andExpect(jsonPath("$.content", hasSize(2)))
				.andExpect(jsonPath("$.content[0].id", equalTo(customer1.getId())))
				.andExpect(jsonPath("$.content[1].id", equalTo(customer2.getId())));
	}

}
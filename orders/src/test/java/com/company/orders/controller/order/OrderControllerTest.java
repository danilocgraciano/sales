package com.company.orders.controller.order;

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

import com.company.orders.builder.OrderBuilder;
import com.company.orders.entity.order.Order;
import com.company.orders.service.order.OrderService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@WebMvcTest(OrderController.class)
public class OrderControllerTest {

	@Autowired
	protected MockMvc mvc;

	@MockBean
	private OrderService service;

	private String uri = "/orders";

	@Test
	public void save() throws Exception {

		ObjectMapper objectMapper = new ObjectMapper();

		Order order = new OrderBuilder().withId(UUID.randomUUID().toString()).buildDefault();
		when(service.save(order)).thenReturn(order);

		mvc.perform(post(this.uri).contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(objectMapper.writeValueAsString(order))).andExpect(status().isCreated())
				.andExpect(header().string("Location", containsString(order.getId())));

	}

	@Test
	public void update() throws Exception {

		ObjectMapper objectMapper = new ObjectMapper();

		Order order = new OrderBuilder().withId(UUID.randomUUID().toString()).buildDefault();
		when(service.save(order)).thenReturn(order);

		mvc.perform(put(this.uri + "/" + order.getId()).contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(objectMapper.writeValueAsString(order))).andExpect(status().isOk());

	}

	@Test
	public void remove() throws Exception {

		Order order = new OrderBuilder().withId(UUID.randomUUID().toString()).buildDefault();
		when(service.findById(order.getId())).thenReturn(order);
		mvc.perform(delete(this.uri + "/" + order.getId()).contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk());

	}

	@Test
	public void findOne() throws Exception {

		Order order = new OrderBuilder().withId(UUID.randomUUID().toString()).buildDefault();

		when(service.findById(order.getId())).thenReturn(order);

		mvc.perform(get(this.uri + "/" + order.getId()).contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk()).andExpect(jsonPath("$.id", equalTo(order.getId())));
	}

	@Test
	public void findAll() throws Exception {

		Order order1 = new OrderBuilder().withId(UUID.randomUUID().toString()).buildDefault();
		Order order2 = new OrderBuilder().withId(UUID.randomUUID().toString()).buildDefault();

		when(service.findAll(PageRequest.of(0, 20))).thenReturn(new PageImpl<Order>(Arrays.asList(order1, order2)));

		mvc.perform(get(this.uri).contentType(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk())
				.andExpect(jsonPath("$.content", hasSize(2)))
				.andExpect(jsonPath("$.content[0].id", equalTo(order1.getId())))
				.andExpect(jsonPath("$.content[1].id", equalTo(order2.getId())));
	}

}

package com.company.orders.controller.item;

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

import com.company.orders.builder.ItemBuilder;
import com.company.orders.entity.item.Item;
import com.company.orders.service.item.ItemService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@WebMvcTest(ItemController.class)
public class ItemControllerTest {

	@Autowired
	protected MockMvc mvc;

	@MockBean
	private ItemService service;

	private String uri = "/items";

	@Test
	public void save() throws Exception {

		ObjectMapper objectMapper = new ObjectMapper();

		Item item = new ItemBuilder().buildDefault();
		when(service.save(item)).thenReturn(item);

		mvc.perform(post(this.uri).contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(objectMapper.writeValueAsString(item))).andExpect(status().isCreated())
				.andExpect(header().string("Location", containsString(item.getSku())));

	}

	@Test
	public void update() throws Exception {

		ObjectMapper objectMapper = new ObjectMapper();

		Item item = new ItemBuilder().buildDefault();
		when(service.save(item)).thenReturn(item);

		mvc.perform(put(this.uri + "/" + item.getSku()).contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(objectMapper.writeValueAsString(item))).andExpect(status().isOk());

	}

	@Test
	public void remove() throws Exception {

		Item item = new ItemBuilder().buildDefault();
		mvc.perform(delete(this.uri + "/" + item.getSku()).contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk());

	}

	@Test
	public void findOne() throws Exception {

		Item item = new ItemBuilder().buildDefault();

		when(service.findById(item.getSku())).thenReturn(item);

		mvc.perform(get(this.uri + "/" + item.getSku()).contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk()).andExpect(jsonPath("$.sku", equalTo(item.getSku())));
	}

	@Test
	public void findAll() throws Exception {

		Item item1 = new ItemBuilder().buildDefault();
		Item item2 = new ItemBuilder().buildDefault();

		when(service.findAll(PageRequest.of(0, 20))).thenReturn(new PageImpl<Item>(Arrays.asList(item1, item2)));

		mvc.perform(get(this.uri).contentType(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk())
				.andExpect(jsonPath("$.content", hasSize(2)))
				.andExpect(jsonPath("$.content[0].sku", equalTo(item1.getSku())))
				.andExpect(jsonPath("$.content[1].sku", equalTo(item2.getSku())));
	}

}
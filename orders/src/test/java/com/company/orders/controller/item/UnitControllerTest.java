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

import com.company.orders.builder.UnitBuilder;
import com.company.orders.entity.item.Unit;
import com.company.orders.service.item.UnitService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@WebMvcTest(UnitController.class)
public class UnitControllerTest {

	@Autowired
	protected MockMvc mvc;

	@MockBean
	private UnitService service;

	private String uri = "/units";

	@Test
	public void save() throws Exception {

		ObjectMapper objectMapper = new ObjectMapper();

		Unit unit = new UnitBuilder().withId(UUID.randomUUID().toString()).buildDefault();
		when(service.save(unit)).thenReturn(unit);

		mvc.perform(post(this.uri).contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(objectMapper.writeValueAsString(unit))).andExpect(status().isCreated())
				.andExpect(header().string("Location", containsString(unit.getId())));

	}

	@Test
	public void update() throws Exception {

		ObjectMapper objectMapper = new ObjectMapper();

		Unit unit = new UnitBuilder().withId(UUID.randomUUID().toString()).buildDefault();
		when(service.save(unit)).thenReturn(unit);

		mvc.perform(put(this.uri + "/" + unit.getId()).contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(objectMapper.writeValueAsString(unit))).andExpect(status().isOk());

	}

	@Test
	public void remove() throws Exception {

		Unit unit = new UnitBuilder().withId(UUID.randomUUID().toString()).buildDefault();
		mvc.perform(delete(this.uri + "/" + unit.getId()).contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk());

	}

	@Test
	public void findOne() throws Exception {

		Unit unit = new UnitBuilder().withId(UUID.randomUUID().toString()).buildDefault();

		when(service.findById(unit.getId())).thenReturn(unit);

		mvc.perform(get(this.uri + "/" + unit.getId()).contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk()).andExpect(jsonPath("$.id", equalTo(unit.getId())));
	}

	@Test
	public void findAll() throws Exception {

		Unit unit1 = new UnitBuilder().withId(UUID.randomUUID().toString()).buildDefault();
		Unit unit2 = new UnitBuilder().withId(UUID.randomUUID().toString()).buildDefault();

		when(service.findAll(PageRequest.of(0, 20))).thenReturn(new PageImpl<Unit>(Arrays.asList(unit1, unit2)));

		mvc.perform(get(this.uri).contentType(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk())
				.andExpect(jsonPath("$.content", hasSize(2)))
				.andExpect(jsonPath("$.content[0].id", equalTo(unit1.getId())))
				.andExpect(jsonPath("$.content[1].id", equalTo(unit2.getId())));
	}

}
package com.company.item.controller;

import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;

import com.company.item.base.BaseTest;
import com.company.item.entity.Unit;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UnitControllerTest extends BaseTest {

	private static String id;

	public UnitControllerTest() {
		super("/units");
	}

	private Unit getUnit() {
		Unit unit = new Unit();
		unit.setDescription("description");
		return unit;
	}

	@Test
	public void test1_save() throws Exception {

		Unit unit = getUnit();

		MvcResult result = mvc
				.perform(post(getUri()).contentType(MediaType.APPLICATION_JSON_VALUE).content(asJson(unit)))
				.andExpect(status().isCreated()).andReturn();

		id = getIdFromLocation(result);

	}

	@Test
	public void test2_one() throws Exception {
		MvcResult response = mvc.perform(get(getUri()).contentType(MediaType.APPLICATION_JSON_VALUE).param("id", id))
				.andExpect(status().isOk()).andReturn();

		assertTrue(response.getResponse().getContentAsString().contains(id));

	}

	@Test
	public void test3_all() throws Exception {
		mvc.perform(get(getUri()).contentType(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk());
	}

	@Test
	public void test4_update() throws Exception {

		Unit unit = getUnit();

		mvc.perform(put(getUri() + "/" + id).contentType(MediaType.APPLICATION_JSON_VALUE).content(asJson(unit)))
				.andExpect(status().isOk());

	}

	@Test
	public void test5_remove() throws Exception {

		mvc.perform(delete(getUri() + "/" + id).contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk());
	}

}
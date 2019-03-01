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
import com.company.item.entity.Item;
import com.company.item.entity.Unit;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ItemControllerTest extends BaseTest {

	private static String sku;

	private static Unit unit;

	public ItemControllerTest() {
		super("/items");
	}

	private Unit createUnit() throws Exception {

		unit = new Unit();
		unit.setDescription("description");

		MvcResult result = mvc
				.perform(post("/units").contentType(MediaType.APPLICATION_JSON_VALUE).content(asJson(unit)))
				.andExpect(status().isCreated()).andReturn();

		String unitId = getIdFromLocation(result);
		unit.setId(unitId);

		return unit;
	}

	private void removeUnit() throws Exception {

		mvc.perform(delete("/units/" + unit.getId()).contentType(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk());

	}
	
	private Unit getUnit() throws Exception{
		if (unit != null)
			return unit;
		return createUnit();
	}

	private Item getItem() throws Exception {
		Item item = new Item();
		item.setName("name");
		item.setSku("sku");
		item.setUnit(getUnit());
		item.setUnitPrice(190.0);
		return item;
	}

	@Test
	public void test1_save() throws Exception {

		Item item = getItem();

		MvcResult result = mvc
				.perform(post(getUri()).contentType(MediaType.APPLICATION_JSON_VALUE).content(asJson(item)))
				.andExpect(status().isCreated()).andReturn();

		sku = getIdFromLocation(result);

	}

	@Test
	public void test2_one() throws Exception {
		MvcResult response = mvc.perform(get(getUri()).contentType(MediaType.APPLICATION_JSON_VALUE).param("sku", sku))
				.andExpect(status().isOk()).andReturn();

		assertTrue(response.getResponse().getContentAsString().contains(sku));

	}

	@Test
	public void test3_all() throws Exception {
		mvc.perform(get(getUri()).contentType(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk());
	}

	@Test
	public void test4_update() throws Exception {

		Item item = getItem();

		mvc.perform(put(getUri() + "/" + sku).contentType(MediaType.APPLICATION_JSON_VALUE).content(asJson(item)))
				.andExpect(status().isOk());

	}

	@Test
	public void test5_remove() throws Exception {

		mvc.perform(delete(getUri() + "/" + sku).contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk());

		removeUnit();
	}

}
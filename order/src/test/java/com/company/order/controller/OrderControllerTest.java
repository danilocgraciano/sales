package com.company.order.controller;

import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;

import com.company.order.base.BaseTest;
import com.company.order.entity.ItemOrder;
import com.company.order.entity.Order;
import com.company.order.entity.OrderStatus;
import com.company.order.entity.customer.Customer;
import com.company.order.entity.customer.DeliveryAddress;
import com.company.order.entity.customer.Gender;
import com.company.order.entity.customer.IdentityType;
import com.company.order.entity.item.Item;
import com.company.order.entity.item.Unit;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class OrderControllerTest extends BaseTest {

	private static String id;

	public OrderControllerTest() {
		super("/orders");
	}

	private Item getItem() {
		Unit unit = new Unit();
		unit.setId(UUID.randomUUID().toString());
		unit.setDescription("Unit Description");

		Item item = new Item();
		item.setSku(UUID.randomUUID().toString());
		item.setName("Test Item");
		item.setUnit(unit);
		return item;
	}

	private ItemOrder[] getItens() {

		List<ItemOrder> itens = new ArrayList<>();

		ItemOrder item = new ItemOrder();
		item.setItem(getItem());
		item.setQuantity(2);
		item.setUnitPrice(1.99);
		itens.add(item);

		return itens.toArray(new ItemOrder[itens.size()]);
	}

	private DeliveryAddress getDeliveryAddress() {
		DeliveryAddress address = new DeliveryAddress();
		address.setCity("City");
		address.setComplement("complement");
		address.setCountry("Country");
		address.setDistrict("district");
		address.setNumber("0");
		address.setState("state");
		address.setStreet("street");
		address.setZipCode("00000-000");
		return address;
	}

	private Customer getCustomer() {

		Customer customer = new Customer();
		customer.setId(UUID.randomUUID().toString());
		customer.setEmail("teste@email.com");
		customer.setGender(Gender.MALE);
		customer.setIdentity("00000000000");
		customer.setIdentityType(IdentityType.CPF);
		customer.setName("Customer");

		return customer;
	}

	private Order getOrder() {
		Order order = new Order();
		order.setCustomer(getCustomer());
		order.setDeliveryAddress(getDeliveryAddress());
		order.addItem(getItens());
		order.setStatus(OrderStatus.CREATED);
		return order;
	}

	@Test
	public void test1_save() throws Exception {
		Order order = getOrder();

		MvcResult result = mvc
				.perform(post(getUri()).contentType(MediaType.APPLICATION_JSON_VALUE).content(asJson(order)))
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

		Order order = getOrder();

		mvc.perform(put(getUri() + "/" + id).contentType(MediaType.APPLICATION_JSON_VALUE).content(asJson(order)))
				.andExpect(status().isOk());

	}

	@Test
	public void test5_remove() throws Exception {

		mvc.perform(delete(getUri() + "/" + id).contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk());

	}

}

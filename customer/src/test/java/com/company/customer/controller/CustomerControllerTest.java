package com.company.customer.controller;

import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;

import com.company.customer.base.BaseTest;
import com.company.customer.entity.Address;
import com.company.customer.entity.AddressType;
import com.company.customer.entity.Customer;
import com.company.customer.entity.Gender;
import com.company.customer.entity.IdentityType;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CustomerControllerTest extends BaseTest {

	public CustomerControllerTest() {
		super("/customers");
	}

	private Customer getCustomer() {
		Customer customer = new Customer();
		customer.setBirthDate(new Date());
		customer.setEmail("teste@email.com");
		customer.setGender(Gender.MALE);
		customer.setIdentity("00000000000");
		customer.setIdentityType(IdentityType.CPF);
		customer.setName("Customer");

		Address address = new Address();
		address.setCity("City");
		address.setComplement("complement");
		address.setCountry("Country");
		address.setCustomer(customer);
		address.setDistrict("district");
		address.setNumber("0");
		address.setState("state");
		address.setStreet("street");
		address.setType(AddressType.MAIN);
		address.setZipCode("00000-000");

		List<Address> addressList = new ArrayList<>();
		addressList.add(address);
		customer.setAddress(addressList);
		return customer;
	}

	@Test
	public void test1_save() throws Exception {

		Customer customer = getCustomer();

		MvcResult result = mvc
				.perform(post(getUri()).contentType(MediaType.APPLICATION_JSON_VALUE).content(asJson(customer)))
				.andExpect(status().isCreated()).andReturn();

		readIdFromLocation(result);

	}

	@Test
	public void test2_one() throws Exception {
		MvcResult response = mvc
				.perform(get(getUri()).contentType(MediaType.APPLICATION_JSON_VALUE).param("id", getId()))
				.andExpect(status().isOk()).andReturn();

		assertTrue(response.getResponse().getContentAsString().contains(getId()));

	}

	@Test
	public void test3_all() throws Exception {
		mvc.perform(get(getUri()).contentType(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk());
	}

	@Test
	public void test4_update() throws Exception {

		Customer customer = getCustomer();

		mvc.perform(put(getUriId()).contentType(MediaType.APPLICATION_JSON_VALUE).content(asJson(customer)))
				.andExpect(status().isOk());

	}

	@Test
	public void test5_remove() throws Exception {

		mvc.perform(delete(getUriId()).contentType(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk());
	}

}
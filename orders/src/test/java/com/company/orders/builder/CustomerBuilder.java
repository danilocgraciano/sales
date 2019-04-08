package com.company.orders.builder;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.company.orders.entity.customer.Address;
import com.company.orders.entity.customer.AddressType;
import com.company.orders.entity.customer.Customer;
import com.company.orders.entity.customer.Gender;
import com.company.orders.entity.customer.IdentityType;

public class CustomerBuilder {

	private List<Address> addressList;
	private Date birthDate;
	private String email;
	private Gender gender;
	private IdentityType identityType;
	private String identity;
	private String name;
	private String id;

	public Customer buildDefault() {

		this.birthDate = new Date();
		this.email = "email@company.com";
		this.gender = Gender.FEMALE;
		this.identityType = IdentityType.CPF;
		this.identity = "000.000.000-00";
		this.name = "person from somewhere";

		Address address = new Address();
		address.setCity("city");
		address.setComplement("complement");
		address.setCountry("country");
		address.setDistrict("district");
		address.setNumber("number");
		address.setState("state");
		address.setStreet("street");
		address.setType(AddressType.MAIN);
		address.setZipCode("zipCode");

		this.addressList = new ArrayList<>();
		addressList.add(address);
		return build();
	}

	public Customer build() {

		Customer customer = new Customer();
		customer.setId(id);
		customer.setAddress(this.addressList);
		customer.setBirthDate(this.birthDate);
		customer.setEmail(this.email);
		customer.setGender(this.gender);
		customer.setIdentityType(this.identityType);
		customer.setIdentity(this.identity);
		customer.setName(this.name);

		return customer;
	}
	
	public CustomerBuilder withId(String id) {
		this.id = id;
		return this;
	}

}

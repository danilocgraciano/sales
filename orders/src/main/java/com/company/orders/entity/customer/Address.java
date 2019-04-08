package com.company.orders.entity.customer;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;

import com.company.orders.repository.customer.constans.AddressConstants;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(schema = AddressConstants.SCHEMA, name = AddressConstants.TABLE)
public class Address {

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name = AddressConstants.ID)
	private String id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = AddressConstants.CUSTOMER_ID)
	@JsonIgnore
	private Customer customer;

	@Column(name = AddressConstants.STREET)
	private String street;

	@Column(name = AddressConstants.NUMBER)
	private String number;

	@Column(name = AddressConstants.COMPLEMENT)
	private String complement;

	@Column(name = AddressConstants.ZIP_CODE)
	private String zipCode;

	@Column(name = AddressConstants.CITY)
	private String city;

	@Column(name = AddressConstants.STATE)
	private String state;

	@Column(name = AddressConstants.COUNTRY)
	private String country;

	@Column(name = AddressConstants.DISTRICT)
	private String district;

	@Column(name = AddressConstants.TYPE)
	@Enumerated(EnumType.STRING)
	@NotNull(message = "Type is required")
	private AddressType type;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getComplement() {
		return complement;
	}

	public void setComplement(String complement) {
		this.complement = complement;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public AddressType getType() {
		return type;
	}

	public void setType(AddressType type) {
		this.type = type;
	}

}

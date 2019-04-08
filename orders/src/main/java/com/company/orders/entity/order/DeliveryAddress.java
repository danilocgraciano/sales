package com.company.orders.entity.order;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

import com.company.orders.repository.order.constants.OrderConstants;

@Embeddable
public class DeliveryAddress {

	@Column(name = OrderConstants.DELIVERY_ADDRESS_STREET)
	@NotNull(message = "Street is required")
	private String street;

	@Column(name = OrderConstants.DELIVERY_ADDRESS_NUMBER)
	@NotNull(message = "Number is required")
	private String number;

	@Column(name = OrderConstants.DELIVERY_ADDRESS_COMPLEMENT)
	private String complement;

	@Column(name = OrderConstants.DELIVERY_ADDRESS_ZIP_CODE)
	@NotNull(message = "Zip Code is required")
	private String zipCode;

	@Column(name = OrderConstants.DELIVERY_ADDRESS_CITY)
	@NotNull(message = "City is required")
	private String city;

	@Column(name = OrderConstants.DELIVERY_ADDRESS_STATE)
	@NotNull(message = "State is required")
	private String state;

	@Column(name = OrderConstants.DELIVERY_ADDRESS_COUNTRY)
	@NotNull(message = "Country is required")
	private String country;

	@Column(name = OrderConstants.DELIVERY_ADDRESS_DISTRICT)
	@NotNull(message = "District is required")
	private String district;

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

}

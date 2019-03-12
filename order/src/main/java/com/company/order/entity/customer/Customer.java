package com.company.order.entity.customer;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.company.order.repository.constants.OrderConstants;

@Embeddable
public class Customer {

	@Column(name = OrderConstants.CUSTOMER_ID)
	@NotBlank(message = "Id is required")
	private String id;

	@Column(name = OrderConstants.CUSTOMER_NAME)
	@NotBlank(message = "Name is required")
	private String name;

	@Column(name = OrderConstants.CUSTOMER_IDENTITY)
	@NotBlank(message = "Identity is required")
	private String identity;

	@Column(name = OrderConstants.CUSTOMER_IDENTITY_TYPE)
	@Enumerated(EnumType.STRING)
	@NotNull(message = "Identity Type is required")
	private IdentityType identityType;

	@Column(name = OrderConstants.CUSTOMER_EMAIL)
	@NotBlank(message = "Email is required")
	private String email;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIdentity() {
		return identity;
	}

	public void setIdentity(String identity) {
		this.identity = identity;
	}

	public IdentityType getIdentityType() {
		return identityType;
	}

	public void setIdentityType(IdentityType identityType) {
		this.identityType = identityType;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}

package com.company.customer.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;

import com.company.customer.repository.constants.CustomerConstants;

@Entity
@Table(schema = CustomerConstants.SCHEMA, name = CustomerConstants.TABLE)
public class Customer {

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name = CustomerConstants.ID)
	private String id;

	@Column(name = CustomerConstants.NAME)
	@NotBlank(message = "Name is required")
	private String name;

	@Column(name = CustomerConstants.IDENTITY)
	@NotBlank(message = "Identity is required")
	private String identity;

	@Column(name = CustomerConstants.IDENTITY_TYPE)
	@Enumerated(EnumType.STRING)
	@NotNull(message = "Identity Type is required")
	private IdentityType identityType;

	@Column(name = CustomerConstants.EMAIL)
	@NotBlank(message = "Email is required")
	private String email;

	@Column(name = CustomerConstants.BIRTH_DATE)
	@Temporal(TemporalType.DATE)
	private Date birthDate;

	@Column(name = CustomerConstants.GENDER)
	@Enumerated(EnumType.STRING)
	@NotNull(message = "Identity Type is required")
	private Gender gender;

	@OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
	@NotNull(message="Address is required")
	private List<Address> address;

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

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public List<Address> getAddress() {
		return address;
	}

	public void setAddress(List<Address> address) {
		this.address = address;
	}

}

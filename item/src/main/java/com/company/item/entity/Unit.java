package com.company.item.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.GenericGenerator;

import com.company.item.repository.constants.UnitConstants;

@Entity
@Table(schema = UnitConstants.SCHEMA, name = UnitConstants.TABLE)
public class Unit {

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name = UnitConstants.ID)
	private String id;

	@Column(name = UnitConstants.DESCRIPTION)
	@NotBlank(message = "Description is required")
	private String description;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}

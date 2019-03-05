package com.company.order.entity.item;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotBlank;

import com.company.order.repository.constants.ItemOrderConstants;

@Embeddable
public class Unit {

	@Column(name = ItemOrderConstants.UNIT_ID)
	@NotBlank(message = "Unit Id is required")
	private String id;

	@Column(name = ItemOrderConstants.UNIT_DESCRIPTION)
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

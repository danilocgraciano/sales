package com.company.order.entity.item;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.company.order.repository.constants.ItemOrderConstants;

@Embeddable
public class Item {

	@Column(name = ItemOrderConstants.ITEM_SKU)
	@NotNull(message = "Item sku is required")
	private String sku;

	@Column(name = ItemOrderConstants.ITEM_NAME)
	@NotBlank(message = "Item Name is required")
	private String name;

	@Embedded
	@NotNull(message = "Item Unit is required")
	private Unit unit;

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Unit getUnit() {
		return unit;
	}

	public void setUnit(Unit unit) {
		this.unit = unit;
	}

}

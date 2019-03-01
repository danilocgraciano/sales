package com.company.item.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.company.item.repository.constants.ItemConstants;

@Entity
@Table(schema = ItemConstants.SCHEMA, name = ItemConstants.TABLE)
public class Item {

	@Id
	@Column(name = ItemConstants.SKU)
	private String sku;

	@Column(name = ItemConstants.NAME)
	@NotBlank(message = "Description is required")
	private String name;

	@Column(name = ItemConstants.UNIT_PRICE)
	private double unitPrice;

	@ManyToOne
	@JoinColumn(name = ItemConstants.UNIT_ID)
	@NotNull(message = "Unit is required")
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

	public double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public Unit getUnit() {
		return unit;
	}

	public void setUnit(Unit unit) {
		this.unit = unit;
	}

}

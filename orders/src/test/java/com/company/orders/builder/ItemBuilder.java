package com.company.orders.builder;

import java.util.UUID;

import com.company.orders.entity.item.Item;
import com.company.orders.entity.item.Unit;

public class ItemBuilder {

	private String name;
	private String sku;
	private Unit unit;
	private double unitPrice;

	public Item buildDefault() {

		this.name = "name";
		this.sku = UUID.randomUUID().toString();
		this.unit = new UnitBuilder().buildDefault();
		this.unitPrice = 1d;
		return build();
	}

	public Item build() {
		Item item = new Item();
		item.setName(name);
		item.setSku(sku);
		item.setUnit(unit);
		item.setUnitPrice(unitPrice);
		return item;
	}

}

package com.company.orders.builder;

import com.company.orders.entity.item.Unit;

public class UnitBuilder {

	private String id;
	private String description;

	public Unit buildDefault() {
//		this.id = UUID.randomUUID().toString();
		this.description = "description";
		return build();
	}

	public Unit build() {
		Unit unit = new Unit();
		unit.setId(id);
		unit.setDescription(description);
		return unit;
	}

	public UnitBuilder withId(String id) {
		this.id = id;
		return this;
	}

}

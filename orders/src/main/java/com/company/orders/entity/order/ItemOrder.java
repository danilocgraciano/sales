package com.company.orders.entity.order;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.company.orders.entity.item.Item;
import com.company.orders.repository.order.constants.ItemOrderConstants;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(schema = ItemOrderConstants.SCHEMA, name = ItemOrderConstants.TABLE)
public class ItemOrder {

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name = ItemOrderConstants.ID)
	private String id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = ItemOrderConstants.ORDER_ID)
	@JsonIgnore
	private Order order;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = ItemOrderConstants.ITEM_SKU)
	private Item item;

	@Column(name = ItemOrderConstants.QUANTITY, scale = 4)
	private double quantity;

	@Column(name = ItemOrderConstants.UNIT_PRICE, scale = 2)
	private double unitPrice;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public double getQuantity() {
		return quantity;
	}

	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}

	public double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}

}

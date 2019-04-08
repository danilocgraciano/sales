package com.company.orders.builder;

import java.util.ArrayList;
import java.util.List;

import com.company.orders.entity.customer.Customer;
import com.company.orders.entity.order.DeliveryAddress;
import com.company.orders.entity.order.ItemOrder;
import com.company.orders.entity.order.Order;
import com.company.orders.entity.order.OrderStatus;

public class OrderBuilder {

	private Customer customer;
	private DeliveryAddress deliveryAddress;
	private String id;
	private ItemOrder[] itensOrder;
	private OrderStatus status = OrderStatus.CREATED;

	public Order buildDefault() {

		this.customer = new CustomerBuilder().buildDefault();
		this.deliveryAddress = getDeliveryAddress();
//		this.id = UUID.randomUUID().toString();
		this.itensOrder = getItens();
		return build();
	}

	private DeliveryAddress getDeliveryAddress() {
		DeliveryAddress deliveryAddress = new DeliveryAddress();
		deliveryAddress.setCity("city");
		deliveryAddress.setComplement("complement");
		deliveryAddress.setCountry("country");
		deliveryAddress.setDistrict("district");
		deliveryAddress.setNumber("number");
		deliveryAddress.setState("state");
		deliveryAddress.setStreet("street");
		deliveryAddress.setZipCode("zipCode");
		return deliveryAddress;
	}

	private ItemOrder[] getItens() {
		List<ItemOrder> itens = new ArrayList<>();

		ItemOrder item1 = new ItemOrder();
//		item1.setId(UUID.randomUUID().toString());
		item1.setItem(new ItemBuilder().buildDefault());
		item1.setQuantity(1d);
		item1.setUnitPrice(1d);

		itens.add(item1);

		ItemOrder item2 = new ItemOrder();
//		item2.setId(UUID.randomUUID().toString());
		item2.setItem(new ItemBuilder().buildDefault());
		item2.setQuantity(2d);
		item2.setUnitPrice(2d);
		itens.add(item2);

		return itens.toArray(new ItemOrder[itens.size()]);
	}

	public Order build() {
		Order order = new Order();
		order.setCustomer(customer);
		order.setDeliveryAddress(deliveryAddress);
		order.setId(id);
		order.addItem(itensOrder);
		order.setStatus(this.status);
		return order;
	}

	public OrderBuilder withStatus(OrderStatus status) {
		this.status = status;
		return this;
	}

}

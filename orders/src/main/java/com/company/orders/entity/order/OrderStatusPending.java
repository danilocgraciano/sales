package com.company.orders.entity.order;

public class OrderStatusPending implements OrderStatusOperations {

	@Override
	public OrderStatus created(Order order) {
		throw new RuntimeException("Pending order cannot be changed to created!");
	}

	@Override
	public OrderStatus pending(Order order) {
		// send pending e-mail
		return OrderStatus.PENDING;
	}

	@Override
	public OrderStatus approved(Order order) {
		// send approved e-mail
		// charge client's credit card
		return OrderStatus.APPROVED;
	}

}

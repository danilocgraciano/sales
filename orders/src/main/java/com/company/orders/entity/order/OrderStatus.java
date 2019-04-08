package com.company.orders.entity.order;

public enum OrderStatus implements OrderStatusOperations {

	CREATED(new OrderStatusCreated()),
	PENDING(new OrderStatusPending()),
	APPROVED(new OrderStatusApproved());

	private final OrderStatusOperations operation;

	OrderStatus(OrderStatusOperations operation) {
		this.operation = operation;
	}

	@Override
	public OrderStatus created(Order order) {
		return operation.created(order);
	}

	@Override
	public OrderStatus pending(Order order) {
		return operation.pending(order);
	}

	@Override
	public OrderStatus approved(Order order) {
		return operation.approved(order);
	}

}

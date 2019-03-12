package com.company.order.entity;

public interface OrderStatusOperations {

	OrderStatus created(Order order);

	OrderStatus pending(Order order);

	OrderStatus approved(Order order);

}

package com.company.orders.entity.order;

public interface OrderStatusOperations {

	OrderStatus created(Order order);

	OrderStatus pending(Order order);

	OrderStatus approved(Order order);

}

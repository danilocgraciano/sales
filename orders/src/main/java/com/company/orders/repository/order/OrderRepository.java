package com.company.orders.repository.order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.company.orders.entity.order.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, String> {

}

package com.company.orders.service.order;

import org.springframework.stereotype.Component;

import com.company.orders.base.BaseMessageService;

@Component
public class OrderMessageService extends BaseMessageService {

	public OrderMessageService() {
		super("order.topic");
	}

}

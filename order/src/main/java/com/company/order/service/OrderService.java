package com.company.order.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.company.order.base.BaseService;
import com.company.order.entity.ItemOrder;
import com.company.order.entity.Order;
import com.company.order.repository.OrderRepository;

@Service
public class OrderService implements BaseService<Order, String> {

	@Autowired
	private OrderRepository repository;

	@Override
	public Order save(Order e) {

		ItemOrder[] itemList = e.getItens();
		for (ItemOrder itemOrder : itemList) {
			itemOrder.setOrder(e);
		}

		validate(e);

		return repository.save(e);
	}

	@Override
	public Order update(String id, Order e) {

		Order order = findById(id);
		order.setCustomer(e.getCustomer());
		order.setDeliveryAddress(e.getDeliveryAddress());
		order.setStatus(e.getStatus());

		order.removeItem(order.getItens());
		if (e.getItens() != null)
			order.addItem(e.getItens());

		validate(order);

		return save(order);
	}

	@Override
	public void deleteById(String id) {
		repository.deleteById(id);
	}

	@Override
	public Order findById(String id) {
		return repository.findById(id)
				.orElseThrow(() -> new EmptyResultDataAccessException("Order not found " + id, 1));
	}

	@Override
	public Page<Order> findAll(Pageable pageable) {
		return repository.findAll(pageable);
	}

	@Override
	public Page<Order> findAll(Order e, Pageable pageable) {
		return repository.findAll(Example.of(e), pageable);
	}

	@Override
	public void validate(Order e) {

		double total = 0d;

		ItemOrder[] itemList = e.getItens();
		for (ItemOrder itemOrder : itemList) {
			double totalItem = itemOrder.getQuantity() * itemOrder.getUnitPrice();
			total += totalItem;
		}

		if (total - e.getAmmount() != 0)
			throw new IllegalArgumentException("Order ammount invalid");
	}

}

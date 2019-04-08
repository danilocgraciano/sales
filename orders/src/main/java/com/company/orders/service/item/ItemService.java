package com.company.orders.service.item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.company.orders.base.BaseService;
import com.company.orders.entity.item.Item;
import com.company.orders.repository.item.ItemRepository;

@Service
public class ItemService implements BaseService<Item, String> {

	@Autowired
	private ItemRepository repository;

	@Override
	public Item save(Item e) {

		return repository.save(e);
	}

	@Override
	public Item update(String id, Item e) {

		Item item = findById(id);
		
		item.setName(e.getName());
		item.setSku(e.getSku());
		item.setUnit(e.getUnit());
		item.setUnitPrice(e.getUnitPrice());
		
		return save(item);
	}

	@Override
	public void deleteById(String id) {
		repository.deleteById(id);
	}

	@Override
	public Item findById(String id) {
		return repository.findById(id).orElseThrow(() -> new EmptyResultDataAccessException("Item not found " + id, 1));
	}

	@Override
	public Page<Item> findAll(Pageable pageable) {
		return repository.findAll(pageable);
	}

	@Override
	public Page<Item> findAll(Item e, Pageable pageable) {
		return repository.findAll(Example.of(e), pageable);
	}

}

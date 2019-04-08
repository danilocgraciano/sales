package com.company.orders.service.item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.company.orders.base.BaseService;
import com.company.orders.entity.item.Unit;
import com.company.orders.repository.item.UnitRepository;

@Service
public class UnitService implements BaseService<Unit, String> {

	@Autowired
	private UnitRepository repository;

	@Override
	public Unit save(Unit e) {

		return repository.save(e);
	}

	@Override
	public Unit update(String id, Unit e) {

		Unit unit = findById(id);
		unit.setDescription(e.getDescription());
		return save(unit);
	}

	@Override
	public void deleteById(String id) {
		repository.deleteById(id);
	}

	@Override
	public Unit findById(String id) {
		return repository.findById(id).orElseThrow(() -> new EmptyResultDataAccessException("Unit not found " + id, 1));
	}

	@Override
	public Page<Unit> findAll(Pageable pageable) {
		return repository.findAll(pageable);
	}

	@Override
	public Page<Unit> findAll(Unit e, Pageable pageable) {
		return repository.findAll(Example.of(e), pageable);
	}

}

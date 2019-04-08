package com.company.orders.base;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BaseService<E, ID> {

	E save(E e);

	E update(ID id, E e);

	void deleteById(ID id);

	E findById(ID id);

	Page<E> findAll(Pageable pageable);

	Page<E> findAll(E e, Pageable pageable);

}

package com.company.orders.service.customer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.company.orders.base.BaseService;
import com.company.orders.entity.customer.Address;
import com.company.orders.entity.customer.Customer;
import com.company.orders.repository.customer.CustomerRepository;

@Service
public class CustomerService implements BaseService<Customer, String> {

	@Autowired
	private CustomerRepository repository;

	@Override
	public Customer save(Customer e) {

		List<Address> adressList = e.getAddress();
		for (Address address : adressList)
			address.setCustomer(e);

		return repository.save(e);
	}

	@Override
	public Customer update(String id, Customer e) {

		Customer customer = findById(id);
		customer.setBirthDate(e.getBirthDate());
		customer.setEmail(e.getEmail());
		customer.setIdentity(e.getIdentity());
		customer.setIdentityType(e.getIdentityType());
		customer.setName(e.getName());

		customer.getAddress().clear();
		if (e.getAddress() != null)
			customer.getAddress().addAll(e.getAddress());

		return save(customer);
	}

	@Override
	public void deleteById(String id) {
		repository.deleteById(id);
	}

	@Override
	public Customer findById(String id) {
		return repository.findById(id).orElseThrow(() -> new EmptyResultDataAccessException("Customer not found " + id, 1));
	}

	@Override
	public Page<Customer> findAll(Pageable pageable) {
		return repository.findAll(pageable);
	}

	@Override
	public Page<Customer> findAll(Customer e, Pageable pageable) {
		return repository.findAll(Example.of(e), pageable);
	}

}

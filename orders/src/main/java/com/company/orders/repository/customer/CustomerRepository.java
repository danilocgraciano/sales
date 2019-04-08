package com.company.orders.repository.customer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.company.orders.entity.customer.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, String>{

}

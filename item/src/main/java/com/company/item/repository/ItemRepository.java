package com.company.item.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.company.item.entity.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item, String>{

}

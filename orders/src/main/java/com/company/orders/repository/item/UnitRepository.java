package com.company.orders.repository.item;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.company.orders.entity.item.Unit;

@Repository
public interface UnitRepository extends JpaRepository<Unit, String> {

}

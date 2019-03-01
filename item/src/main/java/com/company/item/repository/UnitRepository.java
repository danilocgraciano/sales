package com.company.item.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.company.item.entity.Unit;

@Repository
public interface UnitRepository extends JpaRepository<Unit, String> {

}

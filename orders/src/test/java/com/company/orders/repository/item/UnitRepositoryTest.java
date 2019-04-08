package com.company.orders.repository.item;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.junit4.SpringRunner;

import com.company.orders.builder.UnitBuilder;
import com.company.orders.entity.item.Unit;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class UnitRepositoryTest {

	@Autowired
	UnitRepository repository;

	@Test
	public void save() {

		Unit unit = new UnitBuilder().buildDefault();

		repository.save(unit);

		assertNotNull(unit.getId());
	}

	@Test
	public void update() {

		Unit unit = new UnitBuilder().buildDefault();
		repository.save(unit);

		Unit aUnit = repository.findById(unit.getId())
				.orElseThrow(() -> new EmptyResultDataAccessException("Unit not found ", 1));

		aUnit.setDescription("other");
		repository.save(aUnit);

		unit = repository.findById(unit.getId())
				.orElseThrow(() -> new EmptyResultDataAccessException("Unit not found ", 1));

		assertThat(unit.getDescription(), equalTo(aUnit.getDescription()));
	}

	@Test
	public void delete() {

		Unit unit = new UnitBuilder().buildDefault();
		repository.save(unit);

		repository.delete(unit);

		assertThat(!repository.findById(unit.getId()).isPresent());

	}

}

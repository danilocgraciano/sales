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

import com.company.orders.builder.ItemBuilder;
import com.company.orders.entity.item.Item;
import com.company.orders.entity.item.Unit;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class ItemRepositoryTest {

	@Autowired
	ItemRepository itemRepository;

	@Autowired
	UnitRepository unitRepository;

	@Test
	public void save() {

		Item item = new ItemBuilder().buildDefault();
		
		Unit unit = item.getUnit();
		
		unitRepository.save(unit);
		itemRepository.save(item);

		assertNotNull(item.getSku());
	}

	@Test
	public void update() {

		Item item = new ItemBuilder().buildDefault();
		
		Unit unit = item.getUnit();
		
		unitRepository.save(unit);
		itemRepository.save(item);
		
		Item aItem = itemRepository.findById(item.getSku())
				.orElseThrow(() -> new EmptyResultDataAccessException("Item not found ", 1));

		aItem.setName("other");
		itemRepository.save(aItem);

		item = itemRepository.findById(item.getSku())
				.orElseThrow(() -> new EmptyResultDataAccessException("Item not found ", 1));

		assertThat(item.getName(), equalTo(aItem.getName()));
	}

	@Test
	public void delete() {

		Item item = new ItemBuilder().buildDefault();
		
		Unit unit = item.getUnit();
		
		unitRepository.save(unit);
		itemRepository.save(item);
		
		itemRepository.delete(item);

		assertThat(!itemRepository.findById(item.getSku()).isPresent());

	}

}

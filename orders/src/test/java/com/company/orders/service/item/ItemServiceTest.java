package com.company.orders.service.item;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Optional;
import java.util.UUID;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import com.company.orders.builder.ItemBuilder;
import com.company.orders.entity.item.Item;
import com.company.orders.repository.item.ItemRepository;

@RunWith(SpringRunner.class)
public class ItemServiceTest {

	@MockBean
	private ItemRepository repository;

	@Autowired
	private ItemService service;

	@TestConfiguration
	static class ItemServiceTestConfigurationContext {

		@Bean
		public ItemService itemService() {
			return new ItemService();
		}
	}

	@Test
	public void save() {

		Item e = new ItemBuilder().buildDefault();

		when(repository.save(e)).thenReturn(e);

		service.save(e);

		verify(repository, times(1)).save(e);

	}

	@Test
	public void update() {

		Item e = new ItemBuilder().buildDefault();
		e.setSku(UUID.randomUUID().toString());

		when(repository.findById(e.getSku())).thenReturn(Optional.of(e));
		when(repository.save(e)).thenReturn(e);

		service.update(e.getSku(), e);

		verify(repository, times(1)).save(e);

	}

	@Test
	public void deleteById() {

		Item e = new ItemBuilder().buildDefault();
		e.setSku(UUID.randomUUID().toString());

		service.deleteById(e.getSku());

		verify(repository, times(1)).deleteById(e.getSku());

	}

	@Test
	public void findById() {

		Item e = new ItemBuilder().buildDefault();
		e.setSku(UUID.randomUUID().toString());

		when(repository.findById(e.getSku())).thenReturn(Optional.of(e));

		service.findById(e.getSku());

		verify(repository, times(1)).findById(e.getSku());

	}

	@Test
	public void findAll() {

		Item e = new ItemBuilder().buildDefault();
		e.setSku(UUID.randomUUID().toString());

		Pageable pageable = PageRequest.of(0, 20);

		when(repository.findAll(pageable)).thenReturn(new PageImpl<Item>(Arrays.asList(e)));

		service.findAll(pageable);

		verify(repository, times(1)).findAll(pageable);

	}

	@Test
	public void findAllExample() {

		Item e = new ItemBuilder().buildDefault();
		e.setSku(UUID.randomUUID().toString());

		Pageable pageable = PageRequest.of(0, 20);
		Example<Item> example = Example.of(e);

		when(repository.findAll(example, pageable)).thenReturn(new PageImpl<Item>(Arrays.asList(e)));

		service.findAll(e, pageable);

		verify(repository, times(1)).findAll(example, pageable);

	}

}

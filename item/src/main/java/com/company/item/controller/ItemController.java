package com.company.item.controller;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.company.item.entity.Item;
import com.company.item.service.ItemService;

@RestController
@RequestMapping("/items")
public class ItemController {

	@Autowired
	private ItemService service;

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> save(@Valid @RequestBody(required = true) Item item) throws Exception {

		item = service.save(item);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{sku}").buildAndExpand(item.getSku()).toUri();

		return ResponseEntity.created(uri).contentType(MediaType.APPLICATION_JSON_UTF8).body("Created successfully!");
	}

	@PutMapping(value = "/{sku}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public void update(@PathVariable(name = "sku", required = true) String id,
			@Valid @RequestBody(required = true) Item item) throws Exception {

		service.update(id, item);
	}

	@DeleteMapping(value = "/{sku}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public void delete(@PathVariable(name = "sku", required = true) String id) throws Exception {

		service.deleteById(id);
	}

	@GetMapping(value = "/{sku}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Item one(@PathVariable(name = "sku", required = true) String id) throws Exception {

		return service.findById(id);

	}

	@GetMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Page<Item> all(Pageable pageable) throws Exception {

		return service.findAll(pageable);
	}

}

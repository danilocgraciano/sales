package com.company.orders.controller.item;

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

import com.company.orders.entity.item.Unit;
import com.company.orders.service.item.UnitService;

@RestController
@RequestMapping("/units")
public class UnitController {

	@Autowired
	private UnitService service;

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> save(@Valid @RequestBody(required = true) Unit unit) throws Exception {

		service.save(unit);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(unit.getId()).toUri();

		return ResponseEntity.created(uri).contentType(MediaType.APPLICATION_JSON_UTF8).body("Created successfully!");
	}

	@PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public void update(@PathVariable(name = "id", required = true) String id,
			@Valid @RequestBody(required = true) Unit unit) throws Exception {

		service.update(id, unit);
	}

	@DeleteMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public void delete(@PathVariable(name = "id", required = true) String id) throws Exception {

		service.deleteById(id);
	}

	@GetMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Unit one(@PathVariable(name = "id", required = true) String id) throws Exception {

		return service.findById(id);

	}

	@GetMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Page<Unit> all(Pageable pageable) throws Exception {

		return service.findAll(pageable);
	}

}

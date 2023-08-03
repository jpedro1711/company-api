package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.DTO.CustomerDto;
import com.example.demo.domain.Customer;
import com.example.demo.services.CustomerService;

@RestController
@RequestMapping("/customers")
public class CustomerController {
	@Autowired
	private CustomerService customerService;
	
	@GetMapping
	public ResponseEntity<PagedModel<EntityModel<Customer>>> findAll(
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "limit", defaultValue = "15") Integer limit,
			@RequestParam(value = "direction", defaultValue = "asc") String direction
			) {
		var sortDirection = "desc".equalsIgnoreCase(direction) ? Direction.DESC : Direction.ASC;
		Pageable pageable = PageRequest.of(page, limit, Sort.by(sortDirection, "name"));
		return ResponseEntity.status(HttpStatus.OK).body(customerService.findAll(pageable));
	}
	
	@GetMapping(value = "/name/{name}")
	public ResponseEntity<PagedModel<EntityModel<Customer>>> findCustomerByName(
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "limit", defaultValue = "15") Integer limit,
			@PathVariable(value = "name") String name,
			@RequestParam(value = "direction", defaultValue = "asc") String direction
			) {
		var sortDirection = "desc".equalsIgnoreCase(direction) ? Direction.DESC : Direction.ASC;
		Pageable pageable = PageRequest.of(page, limit, Sort.by(sortDirection, "name"));
		return ResponseEntity.status(HttpStatus.OK).body(customerService.findCustomerByName(name, pageable));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Customer> findById(@PathVariable Long id) {
		Customer obj = customerService.findById(id);
		return ResponseEntity.status(HttpStatus.OK).body(obj);
	}
	
	@PostMapping
	public ResponseEntity<Object> save(@RequestBody CustomerDto data) {
		try {
			Customer created = customerService.create(data);
			return ResponseEntity.status(HttpStatus.CREATED).body(created);
		} catch(DataIntegrityViolationException error) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: Duplicated Constraint in CPF or email");
		}
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Customer> update(@RequestBody CustomerDto data, @PathVariable Long id) {
		Customer updated = customerService.update(id, data);
		return ResponseEntity.status(HttpStatus.OK).body(updated);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> remove(@PathVariable Long id) {
		customerService.remove(id);
		return ResponseEntity.noContent().build();
	}
}

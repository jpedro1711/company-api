package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
	public ResponseEntity<List<Customer>> findAll() {
		List<Customer> result= this.customerService.findAll();
		return ResponseEntity.status(HttpStatus.OK).body(result);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Customer> findById(@PathVariable Long id) {
		Customer obj = customerService.findById(id);
		return ResponseEntity.status(HttpStatus.OK).body(obj);
	}
	
	@PostMapping
	public ResponseEntity<Customer> save(@RequestBody CustomerDto data) {
		Customer created = customerService.create(data);
		return ResponseEntity.status(HttpStatus.CREATED).body(created);
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

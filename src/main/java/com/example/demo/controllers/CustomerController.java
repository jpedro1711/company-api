package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}

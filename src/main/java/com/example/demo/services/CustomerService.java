package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.example.demo.DTO.CustomerDto;
import com.example.demo.controllers.CustomerController;
import com.example.demo.domain.Customer;
import com.example.demo.repositories.CustomerRepository;
import com.example.demo.services.exceptions.DatabaseException;
import com.example.demo.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@Service
public class CustomerService {
	
	
	@Autowired
	private CustomerRepository customerRepository;
	
	public List<Customer> findAll() {
		List<Customer> list = this.customerRepository.findAll();
		list
			.stream()
			.forEach(c -> c.add(linkTo(methodOn(CustomerController.class).findById(c.getId())).withSelfRel()));
		return list;
	}
	
	public Customer findById(Long id) {
		Optional<Customer> obj = this.customerRepository.findById(id);
		if (obj.isEmpty()) {
			throw new ResourceNotFoundException("Customer with id " + id + " was not found");
		}
		obj.get().add(linkTo(methodOn(CustomerController.class).findById(id)).withSelfRel());
		return obj.get();
	}
	
	public Customer create(CustomerDto data) {
		var customerModel = new Customer();
		BeanUtils.copyProperties(data, customerModel);
		var saved = customerRepository.save(customerModel);
		saved.add(linkTo(methodOn(CustomerController.class).findById(saved.getId())).withSelfRel());
		return saved;
	}
	
	public Customer update(Long id, CustomerDto data) {
		Customer customer = this.findById(id);
		var customerModel = customer;
		BeanUtils.copyProperties(data, customerModel);
		var updated = customerRepository.save(customer);
		updated.add(linkTo(methodOn(CustomerController.class).findById(updated.getId())).withSelfRel());
		return updated;
	}
	
	public void remove(Long id) {
		try {
			customerRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("Customer with id " + id + " was not found");
		} catch (EntityNotFoundException e) {
			throw new DatabaseException(e.getMessage());
		}
	}
	
}

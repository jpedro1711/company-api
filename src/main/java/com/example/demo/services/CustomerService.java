package com.example.demo.services;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.example.demo.DTO.CustomerResponseDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.PagedModel;
import org.springframework.stereotype.Service;

import com.example.demo.DTO.CustomerDto;
import com.example.demo.controllers.CustomerController;
import com.example.demo.domain.Customer;
import com.example.demo.repositories.CustomerRepository;
import com.example.demo.services.exceptions.ResourceNotFoundException;


@Service
public class CustomerService {
	
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	PagedResourcesAssembler<Customer> assembler;
	
	public PagedModel<EntityModel<Customer>> findAll(Pageable pageable) {
		var list = this.customerRepository.findAll(pageable);
		list
			.stream()
			.forEach(c -> c.add(linkTo(methodOn(CustomerController.class).findById(c.getId())).withSelfRel()));
		Link link = linkTo(methodOn(CustomerController.class).findAll(pageable.getPageNumber(), pageable.getPageSize(), "ASC")).withSelfRel();
		return assembler.toModel(list, link );
	}

	public List<Customer> getCompleteCustomersList() {
		List<Customer> res = customerRepository.findAll();



		return res;
	}
	
	public PagedModel<EntityModel<Customer>> findCustomerByName(String name, Pageable pageable) {
		var list = this.customerRepository.findCustomersByName(name, pageable);
		list
			.stream()
			.forEach(c -> c.add(linkTo(methodOn(CustomerController.class).findById(c.getId())).withSelfRel()));
		Link link = linkTo(methodOn(CustomerController.class).findAll(pageable.getPageNumber(), pageable.getPageSize(), "ASC")).withSelfRel();
		return assembler.toModel(list, link );
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
		var customer = customerRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Customer not found"));
		customerRepository.deleteById(customer.getId());
	}
	
}

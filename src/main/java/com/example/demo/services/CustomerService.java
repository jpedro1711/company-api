package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.example.demo.DTO.CustomerDto;
import com.example.demo.domain.Customer;
import com.example.demo.repositories.CustomerRepository;
import com.example.demo.services.exceptions.DatabaseException;
import com.example.demo.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class CustomerService {
	
	
	@Autowired
	private CustomerRepository customerRepository;
	
	public List<Customer> findAll() {
		return this.customerRepository.findAll();
	}
	
	public Customer findById(Long id) {
		Optional<Customer> obj = this.customerRepository.findById(id);
		if (obj.isEmpty()) {
			throw new ResourceNotFoundException("Customer with id " + id + " was not found");
		}
		return obj.get();
	}
	
	public Customer create(CustomerDto data) {
		var customerModel = new Customer();
		BeanUtils.copyProperties(data, customerModel);
		return customerRepository.save(customerModel);
	}
	
	public Customer update(Long id, CustomerDto data) {
		Customer customer = this.findById(id);
		var customerModel = customer;
		BeanUtils.copyProperties(data, customerModel);
		return customerRepository.save(customer);
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

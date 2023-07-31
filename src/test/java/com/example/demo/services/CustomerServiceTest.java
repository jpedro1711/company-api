package com.example.demo.services;

import static com.example.demo.services.common.Constants.CUSTOMER_DTO;
import static com.example.demo.services.common.Constants.VALID_CUSTOMER;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.demo.domain.Customer;
import com.example.demo.repositories.CustomerRepository;

@TestInstance(Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {
	
	@Mock
	private CustomerRepository repository;
	
	@InjectMocks
	private CustomerService service;

	@Test
	void testFindAll_ReturnsAllCustomers() {
		List<Customer> list = new ArrayList<>();
		list.add(VALID_CUSTOMER);
		when(repository.findAll()).thenReturn(list);
		var result = service.findAll();
		assertNotNull(result);
		assertThat(result.size()).isEqualTo(1);
	}

	@Test
	void FindById_WithExistingId_ReturnOptionalOfCustomer() {
		when(repository.findById(VALID_CUSTOMER.getId())).thenReturn(Optional.of(VALID_CUSTOMER));
		var result = service.findById(VALID_CUSTOMER.getId());
		assertNotNull(result);
		assertNotNull(result.getLinks());
		assertThat(result).isEqualTo(VALID_CUSTOMER);
	}

	@Test
	void createWithValidData_ReturnsCustomer() {
		when(repository.save(any())).thenReturn(VALID_CUSTOMER);
		var result = service.create(CUSTOMER_DTO);
		assertNotNull(result);
		assertNotNull(result.getLinks());
		assertThat(result.getCpf()).isEqualTo(result.getCpf());
		assertThat(result.getEmail()).isEqualTo(result.getEmail());
	}

	@Test
	void updateWithValidIdAndData_ReturnsCustomer() {
		doReturn(Optional.of(VALID_CUSTOMER)).when(repository).findById(1L);
		when(repository.save(any())).thenReturn(VALID_CUSTOMER);
		var result = service.update(1L, CUSTOMER_DTO);
		assertNotNull(result);
		assertNotNull(result.getLinks());
		assertThat(result.getCpf()).isEqualTo(result.getCpf());
		assertThat(result.getEmail()).isEqualTo(result.getEmail());
	}

	@Test
	void removeWithAnExistingId_ReturnsNothing() {
		doNothing().when(repository).deleteById(VALID_CUSTOMER.getId());
		service.remove(VALID_CUSTOMER.getId());
		verify(repository, times(1)).deleteById(VALID_CUSTOMER.getId());
	}

}

package com.example.demo.services.common;

import com.example.demo.DTO.CustomerDto;
import com.example.demo.domain.Customer;

public class Constants {
	public static final CustomerDto CUSTOMER_DTO = new CustomerDto("name", "address", "111.111.111-11", "9999-9999", "email");
	public static final Customer VALID_CUSTOMER = new Customer(1L, "name", "address", "111.111.111-11", "9999-9999", "email");
}

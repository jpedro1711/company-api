package com.example.demo.services;

import com.example.demo.DTO.CustomerResponseDto;
import com.example.demo.domain.CSVHelper;
import com.example.demo.domain.Customer;
import com.example.demo.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.util.List;

@Service
public class CSVService {

    @Autowired
    CustomerService service;

    public ByteArrayInputStream load() {
        List<Customer> customers = service.getCompleteCustomersList();

        ByteArrayInputStream in = CSVHelper.tutorialsToCSV(customers);
        return in;
    }
}

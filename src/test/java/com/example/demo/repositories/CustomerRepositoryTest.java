package com.example.demo.repositories;

import com.example.demo.domain.Customer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.Optional;

import static com.example.demo.services.common.Constants.VALID_CUSTOMER;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DataJpaTest
public class CustomerRepositoryTest {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    @AfterEach
    public void AfterEach() {
        VALID_CUSTOMER.setId(null);
    }

    @Test
    public void createCustomerWithValidData_ReturnsCustomer() {
        Customer customer = customerRepository.save(VALID_CUSTOMER);
        Customer sut = testEntityManager.find(Customer.class, customer.getId());
        assertThat(sut).isNotNull();
        assertThat(sut.getEmail()).isEqualTo(VALID_CUSTOMER.getEmail());
        assertThat(sut.getAddress()).isEqualTo(VALID_CUSTOMER.getAddress());
        assertThat(sut.getName()).isEqualTo(VALID_CUSTOMER.getName());
        assertThat(sut.getPhoneNumber()).isEqualTo(VALID_CUSTOMER.getPhoneNumber());
        assertThat(sut.getLinks()).isNotNull();
    }

    @Test
    public void createCustomerWithInvalidData_ThrowsException() {
        Customer empty = new Customer();
        Customer invalid = new Customer(null, null, null, null, null);
        assertThatThrownBy(() -> customerRepository.save(empty)).isInstanceOf(RuntimeException.class);
        assertThatThrownBy(() -> customerRepository.save(invalid)).isInstanceOf(RuntimeException.class);
    }

    @Test
    public void createCustomerWithDuplicatedEmail_ThrowsException() {
        Customer c1 = new Customer(200L, "c1", "address 1", "1", "john@gmail.com");
        customerRepository.save(c1);
        Customer c2 = new Customer(201L, "c1", "address 1", "1", "john@gmail.com");
        assertThrows(DataIntegrityViolationException.class, () -> customerRepository.save(c2));
    }

    @Test
    public void findCustomerById_ReturnsCustomer() {
        Customer c = customerRepository.save(VALID_CUSTOMER);
        Optional<Customer> cOpt = customerRepository.findById(c.getId());
        assertThat(cOpt).isNotEmpty();
        assertThat(cOpt.get()).isEqualTo(c);
    }

    @Test
    public void findCustomerWithInvalidId_ReturnsEmpty() {
        Customer c1 = new Customer(-1L, "c1", "address 1", "1", "john@gmail.com");
        Customer cOpt = testEntityManager.find(Customer.class, c1.getId());
        assertThat(cOpt).isNull();
    }

    @Test
    public void removeExistingCustomer_ReturnsOk() {
        Customer c = customerRepository.save(VALID_CUSTOMER);
        customerRepository.deleteById(c.getId());
        Customer removed = testEntityManager.find(Customer.class, c.getId());
        assertThat(removed).isNull();
    }

    @Test
    public void removeUnexistingCustomer_ReturnsNoError() {
        assertDoesNotThrow(() -> customerRepository.deleteById(999L));
    }
}

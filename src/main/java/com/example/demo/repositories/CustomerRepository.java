package com.example.demo.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long>{
	
	
	@Query("SELECT c FROM Customer c WHERE c.name LIKE LOWER(CONCAT ('%',:name,'%'))")
	Page<Customer> findCustomersByName(@Param("name") String name, Pageable pageable);
}

package com.bookstore.customerservice.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bookstore.customerservice.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

	List<Customer> findByIdNumber(String idNumber);

}

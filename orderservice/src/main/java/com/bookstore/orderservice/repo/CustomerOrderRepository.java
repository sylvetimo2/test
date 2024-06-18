package com.bookstore.orderservice.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bookstore.orderservice.entity.CustomerOrder;

public interface CustomerOrderRepository extends JpaRepository<CustomerOrder, Long> {

	List<CustomerOrder> findByOrderingCustomerId(Long customer);

}

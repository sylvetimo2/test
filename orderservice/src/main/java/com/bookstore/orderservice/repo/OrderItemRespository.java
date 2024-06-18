package com.bookstore.orderservice.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bookstore.orderservice.entity.OrderItem;

public interface OrderItemRespository extends JpaRepository<OrderItem, Long> {

}

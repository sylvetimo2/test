package com.bookstore.orderservice.api;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bookstore.orderservice.dto.OrderRequest;
import com.bookstore.orderservice.dto.OrderResponse;

@RestController
public class OrderServiceController {
	/**
	 * Create a new order.
	 */
	@PostMapping(value = "/orders")
	public ResponseEntity<OrderResponse> createOrder(OrderRequest orderRequest) {
		return new ResponseEntity<OrderResponse>(new OrderResponse(), HttpStatus.OK);

	}

	/**
	 * Update order status.
	 */
	@PutMapping(value = "/orders/{id}")
	public ResponseEntity<OrderResponse> updateOrderStatus(@PathVariable(value = "id") Optional<String> id) {
		return new ResponseEntity<OrderResponse>(new OrderResponse(), HttpStatus.OK);

	}

	/**
	 * Retrieve order details by ID.
	 */
	@GetMapping("/orders/{id}")
	public ResponseEntity<OrderResponse> getOrder(@PathVariable(value = "id") Optional<String> id) {
		return new ResponseEntity<OrderResponse>(new OrderResponse(), HttpStatus.OK);

	}

	/**
	 * List all orders for a specific customer.
	 */
	@GetMapping("/customers/{customerId}/orders")
	public ResponseEntity<OrderResponse> getCustomerOrders(@PathVariable(value = "id") Optional<String> id) {
		return new ResponseEntity<OrderResponse>(new OrderResponse(), HttpStatus.OK);

	}
}

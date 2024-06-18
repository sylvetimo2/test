package com.bookstore.orderservice.api;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bookstore.orderservice.constant.OrderStatus;
import com.bookstore.orderservice.dto.OrderRequest;
import com.bookstore.orderservice.dto.OrderResponse;
import com.bookstore.orderservice.service.OrderService;

@RestController
public class OrderServiceController {
	@Autowired
	private OrderService orderService;

	/**
	 * Create a new order.
	 */
	@PostMapping(value = "/orders")
	public ResponseEntity<OrderResponse> createOrder(@Valid @RequestBody OrderRequest orderRequest) {
		OrderResponse orderResponse = orderService.createOrder(orderRequest);
		return new ResponseEntity<OrderResponse>(orderResponse, HttpStatus.OK);

	}

	/**
	 * Update order status.
	 */
	@PutMapping(value = "/orders/{id}")
	public ResponseEntity<OrderResponse> updateOrderStatus(@PathVariable(value = "id") Long id,
			@RequestBody OrderStatus orderStatus) {
		OrderResponse orderResponse = orderService.updateOrderStatus(id, orderStatus);
		return new ResponseEntity<OrderResponse>(orderResponse, HttpStatus.OK);

	}

	/**
	 * Retrieve order details by ID.
	 */
	@GetMapping("/orders/{id}")
	public ResponseEntity<OrderResponse> getOrder(@PathVariable(value = "id") Long id) {
		OrderResponse orderResponse = orderService.getOrder(id);
		return new ResponseEntity<OrderResponse>(orderResponse, HttpStatus.OK);

	}

	/**
	 * List all orders for a specific customer.
	 */
	@GetMapping("/customers/{customerId}/orders")
	public ResponseEntity<?> getCustomerOrders(@PathVariable(value = "customerId") Long customerId) {

		return orderService.getCustomerOrders(customerId);

	}
}

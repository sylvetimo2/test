package com.bookstore.orderservice.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.bookstore.orderservice.constant.BookStatus;
import com.bookstore.orderservice.constant.CustomerStatus;
import com.bookstore.orderservice.constant.OrderStatus;
import com.bookstore.orderservice.dto.BookOrder;
import com.bookstore.orderservice.dto.BookResponse;
import com.bookstore.orderservice.dto.CustomerResponse;
import com.bookstore.orderservice.dto.OrderRequest;
import com.bookstore.orderservice.dto.OrderResponse;
import com.bookstore.orderservice.dto.OrderResult;
import com.bookstore.orderservice.entity.CustomerOrder;
import com.bookstore.orderservice.entity.OrderItem;
import com.bookstore.orderservice.repo.CustomerOrderRepository;
import com.bookstore.orderservice.repo.OrderItemRespository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class OrderService {
	@Autowired
	private CustomerService customerService;
	@Autowired
	private CustomerOrderRepository customerOrderRepository;
	@Autowired
	private BookService bookService;
	@Autowired
	private OrderItemRespository orderItemRespository;

	private List<OrderItem> validateBooks(List<BookOrder> orderedbooks, CustomerOrder customerOrder) {
		List<OrderItem> orders = new ArrayList<>();
		orderedbooks.forEach(orderedBook -> {
			BookResponse book = bookService.fetchBookById(orderedBook.getBookId());
			if (book.getStatus().getStatus().equals(BookStatus.SUCCESS)) {
				OrderItem orderItem = new OrderItem();
				orderItem.setCustomerOrder(customerOrder);
				orderItem.setOrderPrice(orderedBook.getPrice());
				orderItem.setOrderedBookId(book.getBookRequest().get(0).getBookId());
				orders.add(orderItem);
			} else {
				log.info("Invalid  Book {} ", orderedBook.getBookId());
			}
		});
		return orders;
	}

	public OrderResponse createOrder(OrderRequest orderRequest) {
		OrderResponse orderResponse = new OrderResponse();
		try {
			CustomerResponse customer = customerService.fetchCustomerById(orderRequest.getCustomerId());
			if (customer.getStatus().getStatus().equals(CustomerStatus.SUCCESS)) {
				CustomerOrder customerOrder = new CustomerOrder();
				customerOrder.setOrderingCustomerId(customer.getCustomerId());
				List<OrderItem> orders = validateBooks(orderRequest.getOrderedbooks(), customerOrder);
				if ((orders.size() > 0) && (orders.size() == orderRequest.getOrderedbooks().size())) {
					customerOrder.setStatus(OrderStatus.SUCCESS);
					customerOrderRepository.save(customerOrder);
					orderItemRespository.saveAll(orders);
					orderResponse.setOrderId(customerOrder.getOrderId());
					orderResponse.setCustomerId(orderRequest.getCustomerId());
					orderResponse.setStatus(orderResponse.new Status(OrderStatus.SUCCESS, "Success"));
					return orderResponse;
				} else {
					orderResponse.setStatus(orderResponse.new Status(OrderStatus.FAILED, "Failed"));
					log.info("Invalid Book ids found {}", orderRequest);
				}
			} else {

				orderResponse.setStatus(orderResponse.new Status(OrderStatus.NOT_FOUND, "customer Not Found"));
				log.info("Customer Not found request {}", orderRequest);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			log.error("Fialed to process request {}  {}", orderRequest, ex);
			orderResponse.setStatus(orderResponse.new Status(OrderStatus.FAILED, "Failed"));
		}
		return orderResponse;
	}

	public ResponseEntity<?> getCustomerOrders(Long customerId) {
		List<OrderResult> orderResponseItem = new ArrayList<>();
		CustomerResponse customer = customerService.fetchCustomerById(customerId);
		if (customer.getStatus().getStatus().equals(CustomerStatus.SUCCESS)) {
			List<CustomerOrder> orders = customerOrderRepository.findByOrderingCustomerId(customerId);
			if (orders.size() > 0) {
				orders.forEach(item -> {
					OrderResult orderResponse = new OrderResult();
					orderResponse.setStatus(orderResponse.new Status(item.getStatus(), "Success"));
					orderResponse.setCustomerId(customerId);
					orderResponse.setOrderId(item.getOrderId());
					orderResponse.setOrderedbooks(item.getOrderedbooks());
					orderResponseItem.add(orderResponse);
				});
				return new ResponseEntity<>(orderResponseItem, HttpStatus.OK);
			} else {
				OrderResponse orderResponse = new OrderResponse();
				orderResponse.setStatus(orderResponse.new Status(OrderStatus.NOT_FOUND, "NO ORDER FOUND"));
				return new ResponseEntity<>(orderResponse, HttpStatus.OK);
			}
		} else {
			OrderResponse orderResponse = new OrderResponse();
			orderResponse.setStatus(orderResponse.new Status(OrderStatus.NOT_FOUND, "Customer Not Found"));
			return new ResponseEntity<>(orderResponse, HttpStatus.OK);
		}
	}

	public OrderResponse getOrder(Long id) {
		OrderResult orderResponse = new OrderResult();
		Optional<CustomerOrder> orders = customerOrderRepository.findById(id);
		if (orders.isPresent()) {
			CustomerOrder order = orders.get();
			orderResponse.setOrderId(order.getOrderId());
			orderResponse.setStatus(orderResponse.new Status(order.getStatus(), "OK"));
			orderResponse.setCustomerId(id);
			orderResponse.setOrderedbooks(order.getOrderedbooks());
			return orderResponse;
		} else {
			orderResponse.setStatus(orderResponse.new Status(OrderStatus.NOT_FOUND, "Not Found"));
			return orderResponse;
		}
	}

	public OrderResponse updateOrderStatus(Long id, OrderStatus orderStatus) {
		OrderResponse orderResponse = new OrderResponse();
		Optional<CustomerOrder> orders = customerOrderRepository.findById(id);
		if (orders.isPresent()) {
			CustomerOrder order = orders.get();
			order.setStatus(orderStatus);
			customerOrderRepository.save(order);
			orderResponse.setStatus(orderResponse.new Status(order.getStatus(), "Success"));
			orderResponse.setCustomerId(id);
			return orderResponse;
		} else {
			orderResponse.setStatus(orderResponse.new Status(OrderStatus.NOT_FOUND, "Not Found"));
			return orderResponse;
		}
	}
}

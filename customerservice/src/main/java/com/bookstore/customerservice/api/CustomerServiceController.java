package com.bookstore.customerservice.api;

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

import com.bookstore.customerservice.dto.CustomerRequest;
import com.bookstore.customerservice.dto.CustomerResponse;
import com.bookstore.customerservice.service.CustomerService;

@RestController
public class CustomerServiceController {
	@Autowired
	private CustomerService customerService;

	/**
	 * Register a new customer.
	 */
	@PostMapping(value = "/customers")
	public ResponseEntity<CustomerResponse> createCustomer(@Valid @RequestBody CustomerRequest customerRequest) {
		CustomerResponse customerResponse = customerService.saveCustomer(customerRequest);
		return new ResponseEntity<>(customerResponse, HttpStatus.OK);
	}

	/**
	 * Update customer details.
	 */
	@PutMapping(value = "/customers/{id}")
	public ResponseEntity<CustomerResponse> updateCustomer(@PathVariable(value = "id") long Id,
			@RequestBody @Valid CustomerRequest customerRequest) {
		CustomerResponse customerResponse = customerService.updateCustomer(Id, customerRequest);

		return new ResponseEntity<>(customerResponse, HttpStatus.CREATED);

	}

	/**
	 * Retrieve customer
	 */
	@GetMapping(value = "/customers/{id}")
	public ResponseEntity<CustomerResponse> getCustomer(@PathVariable(value = "id") long id) {
		CustomerResponse customerResponse = customerService.getCustomer(id);
		return new ResponseEntity<>(customerResponse, HttpStatus.OK);

	}

}

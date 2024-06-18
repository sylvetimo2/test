package com.bookstore.customerservice.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookstore.customerservice.constant.CustomerStatus;
import com.bookstore.customerservice.dto.CustomerRequest;
import com.bookstore.customerservice.dto.CustomerResponse;
import com.bookstore.customerservice.entity.Customer;
import com.bookstore.customerservice.repo.CustomerRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CustomerService {
	@Autowired
	private CustomerRepository customerRepository;

	public CustomerResponse saveCustomer(CustomerRequest customerRequest) {
		CustomerResponse response = new CustomerResponse();
		try {
			if (customerRepository.findByIdNumber(customerRequest.getIdNumber()).isEmpty()) {
				Customer customer = new Customer();
				customer.setFirstName(customerRequest.getFirstName());
				customer.setLastName(customerRequest.getLastName());
				customer.setIdNumber(customerRequest.getIdNumber());
				customer.setOtherName(customerRequest.getOtherName());
				customerRepository.save(customer);
				response.setStatus(response.new Status(CustomerStatus.SUCCESS, "Created"));

				response.setCustomerName(customerRequest.getFirstName() + " " + customerRequest.getLastName());
				response.setCustomerId(customer.getId());
				response.setIdNumber(customer.getIdNumber());

			} else {
				response.setStatus(response.new Status(CustomerStatus.DUPLICATE, "Duplicate Request "));

			}

		} catch (Exception ex) {
			log.error("Failed to process request ", ex);
			response.setStatus(response.new Status(CustomerStatus.ERROR, "Error Failed to process Request "));

		}
		return response;
	}

	public CustomerResponse getCustomer(long id) {
		CustomerResponse response = new CustomerResponse();
		try {
			Optional<Customer> customer = customerRepository.findById(id);
			if (customer.isPresent()) {
				Customer customerToUpdate = customer.get();
				response.setCustomerId(customerToUpdate.getId());
				response.setCustomerName(customerToUpdate.getFirstName() + " " + customerToUpdate.getLastName());
				response.setIdNumber(customerToUpdate.getIdNumber());
				response.setStatus(response.new Status(CustomerStatus.SUCCESS, "Record Found "));

			} else {
				response.setStatus(response.new Status(CustomerStatus.NOT_FOUND, "Record Found "));

			}

		} catch (Exception ex) {
			log.error("Failed to process request ", ex);
			response.setStatus(response.new Status(CustomerStatus.ERROR, "Error Failed to process Request "));

		}
		return response;
	}

	public CustomerResponse updateCustomer(Long id, CustomerRequest customerRequest) {
		CustomerResponse response = new CustomerResponse();
		try {
			Optional<Customer> customer = customerRepository.findById(id);
			if (customer.isPresent()) {
				Customer customerToUpdate = customer.get();
				customerToUpdate.setFirstName(customerRequest.getFirstName());
				customerToUpdate.setLastName(customerRequest.getLastName());
				customerToUpdate.setIdNumber(customerRequest.getIdNumber());
				customerRepository.save(customerToUpdate);
				response.setCustomerName(customerRequest.getFirstName() + " " + customerRequest.getLastName());
				response.setCustomerId(customerToUpdate.getId());
				response.setIdNumber(customerToUpdate.getIdNumber());
				response.setStatus(response.new Status(CustomerStatus.SUCCESS, "Customer Updated"));

			} else {
				response.setStatus(response.new Status(CustomerStatus.NOT_FOUND, "Customer Not Found"));
			}

		} catch (Exception ex) {
			log.error("Failed to process request ", ex);
			response.setStatus(response.new Status(CustomerStatus.ERROR, "Error Failed to process Request "));

		}
		return response;
	}
}

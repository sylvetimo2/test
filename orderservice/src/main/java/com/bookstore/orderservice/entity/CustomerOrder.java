package com.bookstore.orderservice.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@EntityListeners(AuditingEntityListener.class)
@Entity
public class CustomerOrder {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long orderId;
	private Customer orderingCustomer;
	private Book orderedBook;
	private BigDecimal orderPrice;
	private Status Status;
	@CreationTimestamp
	@CreatedDate
	private LocalDateTime dateCreated;
	@LastModifiedDate
	@UpdateTimestamp
	private LocalDateTime dateUpdated;

}

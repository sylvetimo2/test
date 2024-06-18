package com.bookstore.orderservice.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;

@Data
@Entity
public class OrderItem {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "customerOrderId", foreignKey = @ForeignKey(name = "FK_CUSTOMER_ORDER"), nullable = false, updatable = true)
	private CustomerOrder customerOrder;
	@Column
	private Long orderedBookId;
	@Column
	private BigDecimal orderPrice;
}

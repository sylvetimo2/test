package com.bookstore.orderservice.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.bookstore.orderservice.constant.OrderStatus;
import com.bookstore.orderservice.dto.BookOrder;

import lombok.Data;

@Data
@EntityListeners(AuditingEntityListener.class)
@Entity
public class CustomerOrder {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long orderId;

	private Long orderingCustomerId;
	@Enumerated(EnumType.STRING)
	private OrderStatus Status;
	@CreationTimestamp
	@CreatedDate
	private LocalDateTime dateCreated;
	@LastModifiedDate
	@UpdateTimestamp
	private LocalDateTime dateUpdated;
	@OneToMany(mappedBy = "customerOrder")
	private List<OrderItem> orderedItems;

	public List<OrderItem> getOrderedItems() {
		if (orderedItems == null) {
			return new ArrayList<>();
		}
		return orderedItems;
	}

	@Transient
	private List<BookOrder> orderedbooks;

	public List<BookOrder> getOrderedbooks() {
		List<BookOrder> items = new ArrayList<>();
		getOrderedItems().forEach(item -> {
			BookOrder order = new BookOrder();
			order.setBookId(item.getId());
			// order.setAuthorName(item.getOrderedBook().getAuthorName());
			// order.setBookTitle(item.getOrderedBook().getTitle());
			order.setPrice(item.getOrderPrice());
			// order.setSerialNumber(item.getOrderedBook().getSerialNumber());
			items.add(order);
		});
		return items;
	}

}

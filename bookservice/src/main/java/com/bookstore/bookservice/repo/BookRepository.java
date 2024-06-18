package com.bookstore.bookservice.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bookstore.bookservice.entity.Book;

public interface BookRepository extends JpaRepository<Book, Long> {
	List<Book> findBySerialNumber(String serialNumber);
}

package com.bookstore.orderservice.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.bookstore.orderservice.config.TokenApiConfig;
import com.bookstore.orderservice.dto.BookResponse;

import feign.Headers;

@Headers("Content-Type: application/json")
//@FeignClient(name = "BOOK-SERVICE", url = "${book.service.url}", configuration = TokenApiConfig.class)
@FeignClient(name = "BOOK-SERVICE", configuration = TokenApiConfig.class)

public interface BookService {
	@GetMapping(produces = "application/json", value = "/books/{bookid}")
	public BookResponse fetchBookById(@PathVariable("bookid") long bookId);
}

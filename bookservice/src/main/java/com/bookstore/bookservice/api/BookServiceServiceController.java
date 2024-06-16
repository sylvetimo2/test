package com.bookstore.bookservice.api;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bookstore.bookservice.dto.BookRequest;
import com.bookstore.bookservice.dto.BookResponse;
import com.bookstore.bookservice.entity.Book;

import jakarta.validation.Valid;

@RestController
public class BookServiceServiceController {
	/**
	 * Add a new book.
	 */
	@PostMapping(value = "/books")
	public ResponseEntity<BookResponse> createBook(@RequestBody @Valid BookRequest bookRequest) {
		return new ResponseEntity<BookResponse>(new BookResponse(), HttpStatus.CREATED);
	}

	/**
	 * Update book details.○
	 */
	@PutMapping(value = "/books")
	public ResponseEntity<BookResponse> updateBook(@PathVariable(value = "id") String bookId,
			@RequestBody BookRequest bookRequest) {
		return new ResponseEntity<BookResponse>(new BookResponse(), HttpStatus.ACCEPTED);
	}

	/**
	 * Delete a book.○
	 */
	@DeleteMapping(value = "/books")
	public ResponseEntity<BookResponse> deleteBook(@PathVariable(value = "id") String bookId) {
		return new ResponseEntity<BookResponse>(new BookResponse(), HttpStatus.ACCEPTED);
	}

	/**
	 * Retrieve book
	 */
	@GetMapping(value = "/books")
	public ResponseEntity<BookResponse> getBook(@PathVariable(value = "id") String bookId) {
		return new ResponseEntity<BookResponse>(new BookResponse(), HttpStatus.ACCEPTED);
	}

	/**
	 * List all books
	 */
	@GetMapping(value = "/books")
	public ResponseEntity<List<Book>> getBooks() {
		List<Book> books = null;
		return new ResponseEntity<>(books, HttpStatus.OK);
	}
}

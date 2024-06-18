package com.bookstore.bookservice.api;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.bookstore.bookservice.service.BookService;

@RestController
public class BookServiceServiceController {
	@Autowired
	private BookService bookService;

	/**
	 * Add a new book.
	 */
	@PostMapping(value = "/books")
	public ResponseEntity<BookResponse> createBook(@RequestBody @Valid BookRequest bookRequest) {
		BookResponse response = bookService.createBook(bookRequest);
		return new ResponseEntity<BookResponse>(response, HttpStatus.OK);
	}

	/**
	 * Update book details.○
	 */
	@PutMapping(value = "/books/{id}")
	public ResponseEntity<BookResponse> updateBook(@PathVariable(value = "id") Long bookId,
			@RequestBody BookRequest bookRequest) {
		BookResponse response = bookService.updateBook(bookRequest, bookId);
		return new ResponseEntity<BookResponse>(response, HttpStatus.OK);
	}

	/**
	 * Delete a book.○
	 */
	@DeleteMapping(value = "/books/{id}")
	public ResponseEntity<BookResponse> deleteBook(@PathVariable(value = "id") long bookId) {
		BookResponse res = bookService.delete(bookId);
		return new ResponseEntity<BookResponse>(res, HttpStatus.OK);
	}

	/**
	 * Retrieve book
	 */
	@GetMapping(value = "/books/{id}")
	public ResponseEntity<?> getBook(@PathVariable(value = "id") Long bookId) {

		return bookService.getBook(bookId);
	}

	/**
	 * List all books
	 */
	@GetMapping(value = "/books")
	public ResponseEntity<?> getBooks() {

		return bookService.getBooks();
	}
}

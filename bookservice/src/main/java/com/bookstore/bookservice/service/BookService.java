package com.bookstore.bookservice.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.bookstore.bookservice.constants.BookStatus;
import com.bookstore.bookservice.dto.BookRequest;
import com.bookstore.bookservice.dto.BookResponse;
import com.bookstore.bookservice.entity.Book;
import com.bookstore.bookservice.repo.BookRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class BookService {
	@Autowired
	BookRepository bookRepository;

	/**
	 *
	 * @param bookRequest
	 * @return
	 */
	public BookResponse createBook(BookRequest bookRequest) {
		BookResponse response = new BookResponse();
		try {
			if (bookRepository.findBySerialNumber(bookRequest.getSerialNumber()).isEmpty()) {
				Book book = new Book();
				book.setAuthorName(bookRequest.getAuthorName());
				book.setSerialNumber(bookRequest.getSerialNumber());
				book.setTitle(bookRequest.getBookTitle());
				bookRepository.save(book);
				response.setStatus(response.new Status(BookStatus.SUCCESS, "Book Created "));

			} else {

				response.setStatus(response.new Status(BookStatus.DUPLICATE, "Duplicate Request "));

			}

		} catch (Exception ex) {
			log.error("Failed to process request ", ex);

			response.setStatus(response.new Status(BookStatus.ERROR, "Error Failed to process Request"));

		}
		return response;
	}

	public BookResponse updateBook(BookRequest bookRequest, long bookid) {
		BookResponse response = new BookResponse();
		try {
			Optional<Book> book = bookRepository.findById(bookid);
			if (book.isPresent()) {
				Book bookToUpdate = book.get();
				bookToUpdate.setAuthorName(bookRequest.getAuthorName());
				bookToUpdate.setTitle(bookRequest.getBookTitle());
				bookToUpdate.setSerialNumber(bookRequest.getSerialNumber());
				bookRepository.save(bookToUpdate);
				response.setStatus(response.new Status(BookStatus.SUCCESS, "Book Updated"));

			} else {

				response.setStatus(response.new Status(BookStatus.NOT_FOUND, "Book Not Found "));

			}

		} catch (Exception ex) {
			log.error("Failed to process request ", ex);

			response.setStatus(response.new Status(BookStatus.ERROR, "Error Failed to process Request "));

		}
		return response;
	}

	public ResponseEntity<BookResponse> getBook(long bookid) {
		BookResponse response = new BookResponse();
		try {
			Optional<Book> book = bookRepository.findById(bookid);
			if (book.isPresent()) {
				Book bookToUpdate = book.get();
				BookRequest bookRequest = new BookRequest();
				bookRequest.setAuthorName(bookToUpdate.getAuthorName());
				bookRequest.setBookTitle(bookToUpdate.getTitle());
				bookRequest.setSerialNumber(bookToUpdate.getSerialNumber());
				bookRequest.setBookId(bookToUpdate.getId());
				response.getBookRequest().add(bookRequest);
				response.setStatus(response.new Status(BookStatus.SUCCESS, "Book  Found "));
				return new ResponseEntity<>(response, HttpStatus.OK);
			} else {
				response.setStatus(response.new Status(BookStatus.NOT_FOUND, "Book Not Found "));

				return new ResponseEntity<>(response, HttpStatus.OK);
			}

		} catch (Exception ex) {
			log.error("Failed to process request ", ex);
			response.setStatus(response.new Status(BookStatus.ERROR, "Error Failed to process Request"));
			return new ResponseEntity<>(response, HttpStatus.OK);
		}

	}

	public BookResponse delete(long bookid) {
		BookResponse response = new BookResponse();
		try {
			Optional<Book> book = bookRepository.findById(bookid);
			if (book.isPresent()) {
				Book bookToUpdate = book.get();
				bookRepository.delete(bookToUpdate);
				response.setStatus(response.new Status(BookStatus.SUCCESS, "Book Deleted"));

			} else {

				response.setStatus(response.new Status(BookStatus.NOT_FOUND, "Book Not Found "));

			}
		} catch (Exception ex) {
			log.error("Failed to process request ", ex);

			response.setStatus(response.new Status(BookStatus.ERROR, "Error Failed to process Request "));

		}
		return response;
	}

	/**
	 *
	 * @param bookRequest
	 * @return
	 */
	public ResponseEntity<?> getBooks() {
		List<BookRequest> result = new ArrayList<>();
		try {
			List<Book> books = bookRepository.findAll();
			books.forEach(book -> {
				BookRequest response = new BookRequest();
				response.setAuthorName(book.getAuthorName());
				response.setBookTitle(book.getTitle());
				response.setSerialNumber(book.getSerialNumber());
				response.setBookId(book.getId());
				result.add(response);

			});
			BookResponse bookResponse = new BookResponse();
			bookResponse.setStatus(bookResponse.new Status(BookStatus.SUCCESS, "Success"));
			bookResponse.setBookRequest(result);
			return new ResponseEntity(bookResponse, HttpStatus.OK);

		} catch (Exception ex) {
			log.error("Failed to process request ", ex);
			BookResponse response3 = new BookResponse();
			// response3.setBookId(bookid);
			response3.setStatus(response3.new Status(BookStatus.ERROR, "Error Failed to process Request "));
			return new ResponseEntity(response3, HttpStatus.OK);
		}

	}
}

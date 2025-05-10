package com.cts.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cts.dto.BookDTO;
import com.cts.service.BookService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
@Slf4j
public class BookController {

	private final BookService bookService;

	// Add a new book
	@PostMapping("/addbook")
	public BookDTO addBook(@RequestBody BookDTO bookDTO) {
		log.info("Request to add book: {}", bookDTO.getTitle());
		return bookService.addBook(bookDTO);
	}

	// Update a book by ID
	@PutMapping("/updatebook/{id}")
	public BookDTO updateBook(@PathVariable Long id, @RequestBody BookDTO bookDTO) {
		log.info("Request to update book ID: {}", id);
		return bookService.updateBook(id, bookDTO);
	}

	// Delete a book by ID
	@DeleteMapping("/deletebook/{id}")
	public String deleteBook(@PathVariable Long id) {
		log.info("Request to delete book ID: {}", id);
		bookService.deleteBook(id);
		return "Book deleted successfully.";
	}

	// Get book by ID
	@GetMapping("/getBookById/{id}")
	public BookDTO getBookById(@PathVariable Long id) {
		log.info("Request to get book ID: {}", id);
		return bookService.getBookById(id);
	}

	// Get all books
	@GetMapping("/getAllBooks/all")
	public List<BookDTO> getAllBooks() {
		log.info("Request to get all books");
		return bookService.getAllBooks();
	}

	// Search by title
	@GetMapping("/search/title/{title}")
	public List<BookDTO> searchByTitle(@PathVariable String title) {
		log.info("Request to search books by title: {}", title);
		return bookService.searchByTitle(title);
	}

	// Search by author
	@GetMapping("/search/author/{author}")
	public List<BookDTO> searchByAuthor(@PathVariable String author) {
		log.info("Request to search books by author: {}", author);
		return bookService.searchByAuthor(author);
	}

	// Search by genre
	@GetMapping("/search/genre/{genre}")
	public List<BookDTO> searchByGenre(@PathVariable String genre) {
		log.info("Request to search books by genre: {}", genre);
		return bookService.searchByGenre(genre);
	}

	// Check if book exists
	@GetMapping("/bookexists/{id}")
	public boolean isBookExist(@PathVariable Long id) {
		log.info("Request to check if book exists ID: {}", id);
		return bookService.isBookExist(id);
	}

	// Check if book is available
	@GetMapping("/bookavailable/{id}")
	public boolean isBookAvailable(@PathVariable Long id) {
		log.info("Request to check availability for book ID: {}", id);
		return bookService.isBookAvailable(id);
	}
}
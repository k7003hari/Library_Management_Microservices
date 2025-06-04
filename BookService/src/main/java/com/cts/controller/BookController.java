package com.cts.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.cts.model.Book;
import com.cts.service.BookService;

import lombok.AllArgsConstructor;

@RestController
//@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/books")
@AllArgsConstructor
public class BookController {

	private final BookService bookService;

	@PostMapping("/addbook")
	@ResponseStatus(HttpStatus.CREATED)
	public Book addBook(@RequestBody Book book) {
		return bookService.addBook(book);
	}

	@PutMapping("/update/{id}")
	public Book updateBook(@PathVariable Long id, @RequestBody Book book) {
		return bookService.updateBook(id, book);
	}

	@DeleteMapping("/delete/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteBook(@PathVariable Long id) {
		bookService.deleteBook(id);
	}

	@GetMapping("/getall")
	public List<Book> getAllBooks() {
		return bookService.getAllBooks();
	}

	@GetMapping("/getById/{id}")
	public Book getBookById(@PathVariable Long id) {
		return bookService.getBookById(id);
	}

	@GetMapping("/search/title/{title}")
	public List<Book> searchByTitle(@PathVariable("title") String title) {
		return bookService.searchByTitle(title);
	}

	@GetMapping("/search/author/{author}")
	public List<Book> searchByAuthor(@PathVariable("author") String author) {
		return bookService.searchByAuthor(author);
	}

	@GetMapping("/search/genre/{genre}")
	public List<Book> searchByGenre(@PathVariable("genre") String genre) {
		return bookService.searchByGenre(genre);
	}

	@GetMapping("/member/{id}/{Email}")
	public Book getBookForMember(@PathVariable Long id, @PathVariable("Email") String memberEmail) {
		return bookService.getBookForMember(id, memberEmail);
	}

	@PutMapping("/{id}/copies")
	public void updateBookCopies(@PathVariable Long id, @PathVariable("count") int availableCopies) {
		bookService.updateBookCopies(id, availableCopies);
	}
}

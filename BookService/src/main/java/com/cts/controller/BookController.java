package com.cts.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cts.dto.BookDTO;
import com.cts.model.Book;
import com.cts.service.BookService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController {
 
    private final BookService bookService;
 
    @PostMapping
    public ResponseEntity<String> addBook(@Valid @RequestBody BookDTO bookDTO) {
        bookService.addBook(bookDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body("Book added successfully.");
    }
 
    @PutMapping("/{id}")
    public ResponseEntity<String> updateBook(@PathVariable Long id, @Valid @RequestBody BookDTO bookDTO) {
        bookService.updateBook(id, bookDTO);
        return ResponseEntity.ok("Book updated successfully.");
    }
 
    @GetMapping("/{id}")
    public ResponseEntity<BookDTO> getBook(@PathVariable Long id) {
        return ResponseEntity.ok(bookService.getBookById(id));
    }
 
    @GetMapping
    public ResponseEntity<List<BookDTO>> getAllBooks() {
        return ResponseEntity.ok(bookService.getAllBooks());
    }
 
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return ResponseEntity.ok("Book deleted successfully.");
    }
 
    @GetMapping("/search/title")
    public ResponseEntity<List<BookDTO>> searchByTitle(@RequestParam String title) {
        return ResponseEntity.ok(bookService.searchByTitle(title));
    }
 
    @GetMapping("/search/author")
    public ResponseEntity<List<BookDTO>> searchByAuthor(@RequestParam String author) {
        return ResponseEntity.ok(bookService.searchByAuthor(author));
    }
 
    @GetMapping("/search/genre")
    public ResponseEntity<List<BookDTO>> searchByGenre(@RequestParam String genre) {
        return ResponseEntity.ok(bookService.searchByGenre(genre));
    }
 
    @GetMapping("/exists/{bookId}")
    public ResponseEntity<Boolean> existsById(@PathVariable Long bookId) {
        return ResponseEntity.ok(bookService.isBookExists(bookId));
    }
 
    @GetMapping("/available/{bookId}")
    public ResponseEntity<Boolean> isBookAvailable(@PathVariable Long bookId) {
        return ResponseEntity.ok(bookService.isBookAvailable(bookId));
    }
}
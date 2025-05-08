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
import com.cts.model.Book;
import com.cts.service.BookService;

import lombok.RequiredArgsConstructor;
 
@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController {
 
    private final BookService bookService;
 
    // Existing endpoints
    @PostMapping("/addbook")
    public Book addBook(@RequestBody BookDTO dto) {
        return bookService.addBook(dto);
    }
 
    @GetMapping("/get/{id}")
    public Book getBook(@PathVariable Long id) {
        return bookService.getBookById(id);
    }
 
    @GetMapping("/getallbooks")
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }
 
    @PutMapping("/update/{id}")
    public Book updateBook(@PathVariable Long id, @RequestBody BookDTO dto) {
        return bookService.updateBook(id, dto);
    }
 
    @DeleteMapping("/delete/{id}")
    public void deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
    }
 
    // Feign endpoints
    @GetMapping("/exists/{bookId}")
    public boolean bookExists(@PathVariable Long bookId) {
        return bookService.existsById(bookId);
    }
 
    @GetMapping("/avail/{bookId}/")
    public boolean isBookAvailable(@PathVariable Long bookId) {
        return bookService.isBookAvailable(bookId);
    }
}
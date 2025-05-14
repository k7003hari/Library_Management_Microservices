package com.cts.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.cts.dto.BookDTO;
import com.cts.service.BookService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController {
 
    private final BookService bookService;
 
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BookDTO addBook(@RequestBody BookDTO dto) {
        return bookService.addBook(dto);
    }
 
    @PutMapping("/{id}")
    public BookDTO updateBook(@PathVariable Long id, @RequestBody BookDTO dto) {
        return bookService.updateBook(id, dto);
    }
 
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
    }
 
    @GetMapping("/{id}")
    public BookDTO getBook(@PathVariable Long id) {
        return bookService.getBook(id);
    }
 
    @GetMapping("/search/title")
    public List<BookDTO> searchByTitle(@RequestParam String title) {
        return bookService.searchByTitle(title);
    }
 
    @GetMapping("/search/author")
    public List<BookDTO> searchByAuthor(@RequestParam String author) {
        return bookService.searchByAuthor(author);
    }
 
    @GetMapping("/search/genre")
    public List<BookDTO> searchByGenre(@RequestParam String genre) {
        return bookService.searchByGenre(genre);
    }
 
    @GetMapping("/secure/{id}")
    public BookDTO getBookForMember(@PathVariable Long id, @RequestParam String memberEmail) {
        return bookService.getBookForMember(id, memberEmail);
    }
}
package com.cts.service;

import java.util.List;

import com.cts.model.Book;

public interface BookService {
    Book addBook(Book book);
    Book updateBook(Long id, Book book);
    void deleteBook(Long id);
    Book getBookById(Long id);
    List<Book> searchBooks(String keyword);
    List<Book> getAllBooks(); // Added
}
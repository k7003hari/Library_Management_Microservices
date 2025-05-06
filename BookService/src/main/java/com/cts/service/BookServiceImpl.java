package com.cts.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.exception.BookNotFoundException;
import com.cts.model.Book;
import com.cts.repository.BookRepository;

@Service
public class BookServiceImpl implements BookService {
 
    @Autowired
    private BookRepository bookRepository;
 
    @Override
    public Book addBook(Book book) {
        return bookRepository.save(book);
    }
 
    @Override
    public Book updateBook(Long id, Book book) {
        Book existing = bookRepository.findById(id)
            .orElseThrow(() -> new BookNotFoundException("Book not found with id: " + id));
        book.setBookId(id);
        return bookRepository.save(book);
    }
 
    @Override
    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }
 
    @Override
    public Book getBookById(Long id) {
        return bookRepository.findById(id)
            .orElseThrow(() -> new BookNotFoundException("Book not found with id: " + id));
    }
 
    @Override
    public List<Book> searchBooks(String keyword) {
        return bookRepository.findByTitleContainingIgnoreCase(keyword);
    }
 
    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }
}
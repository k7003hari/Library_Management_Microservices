package com.cts.service;

import java.util.List;

import com.cts.dto.BookDTO;
import com.cts.model.Book;

public interface BookService {
    Book addBook(BookDTO bookDTO);
    Book updateBook(Long bookId, BookDTO bookDTO);
    void deleteBook(Long bookId);
    Book getBookById(Long bookId);
    boolean existsById(Long bookId);
    
    boolean isBookAvailable(Long bookId);
    List<Book> getAllBooks();
    List<Book> searchByTitle(String title);
    List<Book> searchByAuthor(String author);
    List<Book> searchByGenre(String genre);
}
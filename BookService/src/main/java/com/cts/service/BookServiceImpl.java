package com.cts.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cts.dto.BookDTO;
import com.cts.exception.BookNotFoundException;
import com.cts.model.Book;
import com.cts.repository.BookRepository;

import lombok.RequiredArgsConstructor;
 
@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
 
    private final BookRepository bookRepository;
 
    @Override
    public Book addBook(BookDTO dto) {
        Book book = Book.builder()
                .title(dto.getTitle())
                .author(dto.getAuthor())
                .genre(dto.getGenre())
                .isbn(dto.getIsbn())
                .yearPublished(dto.getYearPublished())
                .availableCopies(dto.getAvailableCopies())
                .build();
        return bookRepository.save(book);
    }
 
    @Override
    public Book updateBook(Long bookId, BookDTO dto) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new BookNotFoundException("Book not found"));
        book.setTitle(dto.getTitle());
        book.setAuthor(dto.getAuthor());
        book.setGenre(dto.getGenre());
        book.setIsbn(dto.getIsbn());
        book.setYearPublished(dto.getYearPublished());
        book.setAvailableCopies(dto.getAvailableCopies());
        return bookRepository.save(book);
    }
 
    @Override
    public void deleteBook(Long bookId) {
        if (!bookRepository.existsById(bookId)) {
            throw new BookNotFoundException("Book not found");
        }
        bookRepository.deleteById(bookId);
    }
 
    @Override
    public Book getBookById(Long bookId) {
        return bookRepository.findById(bookId)
                .orElseThrow(() -> new BookNotFoundException("Book not found"));
    }
 
    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }
 
    @Override
    public boolean existsById(Long bookId) {
        return bookRepository.existsById(bookId);
    }
     
    @Override
    public boolean isBookAvailable(Long bookId) {
        return bookRepository.findById(bookId)
                .map(book -> book.getAvailableCopies() > 0)
                .orElse(false);
    }
    
    @Override
    public List<Book> searchByTitle(String title) {
        return bookRepository.findByTitleContainingIgnoreCase(title);
    }
 
    @Override
    public List<Book> searchByAuthor(String author) {
        return bookRepository.findByAuthorContainingIgnoreCase(author);
    }
 
    @Override
    public List<Book> searchByGenre(String genre) {
        return bookRepository.findByGenreContainingIgnoreCase(genre);
    }
}
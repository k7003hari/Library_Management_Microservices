package com.cts;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.cts.model.Book;
import com.cts.service.BookService;

@SpringBootTest
@ActiveProfiles("test") // assumes your test profile uses MySQL
class BookServiceTests {
 
    @Autowired
    private BookService bookService;
 
    private Book sampleBook;
 
    @BeforeEach
    void init() {
        sampleBook = Book.builder()
                .title("Clean Code")
                .author("Robert C. Martin")
                .genre("Programming")
                .isbn("9780132350884")
                .yearPublished(2008)
                .availableCopies(5)
                .build();
    }
 
    @Test
    void testAddBook() {
        Book saved = bookService.addBook(sampleBook);
        assertNotNull(saved.getBookId());
        assertEquals("Clean Code", saved.getTitle());
    }
 
    @Test
    void testUpdateBook() {
        Book saved = bookService.addBook(sampleBook);
        saved.setTitle("Clean Code Updated");
 
        Book updated = bookService.updateBook(saved.getBookId(), saved);
        assertEquals("Clean Code Updated", updated.getTitle());
    }
 
    @Test
    void testDeleteBook() {
        Book saved = bookService.addBook(sampleBook);
        assertDoesNotThrow(() -> bookService.deleteBook(saved.getBookId()));
    }
 
    @Test
    void testSearchByTitle() {
        bookService.addBook(sampleBook);
        List<Book> found = bookService.searchByTitle("Clean");
        assertFalse(found.isEmpty());
    }
 
    @Test
    void testGetBook() {
        Book saved = bookService.addBook(sampleBook);
        Book found = bookService.getBookById(saved.getBookId());
        assertEquals("Clean Code", found.getTitle());
    }
}
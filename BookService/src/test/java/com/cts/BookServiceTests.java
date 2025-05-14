package com.cts;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.cts.dto.BookDTO;
import com.cts.service.BookService;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test") // assumes your test profile uses MySQL
class BookServiceTests {
 
    @Autowired
    private BookService bookService;
 
    private BookDTO sampleBook;
 
    @BeforeEach
    void init() {
        sampleBook = BookDTO.builder()
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
        BookDTO saved = bookService.addBook(sampleBook);
        assertNotNull(saved.getBookId());
        assertEquals("Clean Code", saved.getTitle());
    }
 
    @Test
    void testUpdateBook() {
        BookDTO saved = bookService.addBook(sampleBook);
        saved.setTitle("Clean Code Updated");
 
        BookDTO updated = bookService.updateBook(saved.getBookId(), saved);
        assertEquals("Clean Code Updated", updated.getTitle());
    }
 
    @Test
    void testDeleteBook() {
        BookDTO saved = bookService.addBook(sampleBook);
        assertDoesNotThrow(() -> bookService.deleteBook(saved.getBookId()));
    }
 
    @Test
    void testSearchByTitle() {
        bookService.addBook(sampleBook);
        List<BookDTO> found = bookService.searchByTitle("Clean");
        assertFalse(found.isEmpty());
    }
 
    @Test
    void testGetBook() {
        BookDTO saved = bookService.addBook(sampleBook);
        BookDTO found = bookService.getBook(saved.getBookId());
        assertEquals("Clean Code", found.getTitle());
    }
}
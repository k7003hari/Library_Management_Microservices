package com.cts;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import com.cts.exception.BookNotFoundException;
import com.cts.model.Book;
import com.cts.repository.BookRepository;
import com.cts.service.BookService;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class BookServiceApplicationTests {
 
    @Mock
    private BookRepository bookRepository;
 
    @InjectMocks
    private BookService bookService;
 
    @Test
    public void testAddBook() {
        Book book = new Book(null, "Title", "Author", "Genre", "ISBN", 2022, 5);
        when(bookRepository.save(any(Book.class))).thenReturn(book);
        Book result = bookService.addBook(book);
        assertEquals("Title", result.getTitle());
    }
 
    @Test
    public void testUpdateBook_NotFound() {
        when(bookRepository.findById(1L)).thenReturn(Optional.empty());
        Book book = new Book();
        assertThrows(BookNotFoundException.class, () -> bookService.updateBook(1L, book));
    }
}

package com.cts;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.cts.exception.DuplicateResourceException;
import com.cts.dto.BookDTO;
import com.cts.exception.BookNotFoundException;
import com.cts.model.Book;
import com.cts.repository.BookRepository;
import com.cts.service.BookServiceImpl;
 
public class BookServiceImplTest {
 
    @Mock
    private BookRepository bookRepository;
 
    @InjectMocks
    private BookServiceImpl bookService;
 
    private Book book;
    private BookDTO bookDTO;
 
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        book = new Book(1L, "Title A", "Author A", "Genre A", "ISBN001", 2020, 5);
        bookDTO = new BookDTO(1L, "Title A", "Author A", "Genre A", "ISBN001", 2020, 5);
    }
 
    @Test
    public void testAddBook_Success() {
        when(bookRepository.existsByIsbn("ISBN001")).thenReturn(false);
        when(bookRepository.save(any(Book.class))).thenReturn(book);
 
        BookDTO result = bookService.addBook(bookDTO);
        assertEquals(bookDTO.getTitle(), result.getTitle());
    }
 
    @Test
    public void testAddBook_DuplicateISBN() {
        when(bookRepository.existsByIsbn("ISBN001")).thenReturn(true);
 
        assertThrows(DuplicateResourceException.class, () -> {
            bookService.addBook(bookDTO);
        });
    }
 
    @Test
    public void testGetAllBooks() {
        when(bookRepository.findAll()).thenReturn(Collections.singletonList(book));
        List<BookDTO> result = bookService.getAllBooks();
        assertEquals(1, result.size());
    }
 
    @Test
    public void testGetBookById_Found() {
        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));
        BookDTO result = bookService.getBookById(1L);
        assertEquals("Title A", result.getTitle());
    }
 
    @Test
    public void testGetBookById_NotFound() {
        when(bookRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(BookNotFoundException.class, () -> bookService.getBookById(1L));
    }
 
    @Test
    public void testUpdateBook_Success() {
        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));
        when(bookRepository.save(any(Book.class))).thenReturn(book);
 
        BookDTO result = bookService.updateBook(1L, bookDTO);
        assertEquals(bookDTO.getAuthor(), result.getAuthor());
    }
 
    @Test
    public void testDeleteBook_Success() {
        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));
        doNothing().when(bookRepository).deleteById(1L);
 
        assertDoesNotThrow(() -> bookService.deleteBook(1L));
    }
 
    @Test
    public void testSearchByTitle() {
        when(bookRepository.findByTitleContainingIgnoreCase("Title")).thenReturn(Collections.singletonList(book));
        List<BookDTO> result = bookService.searchByTitle("Title");
        assertEquals(1, result.size());
    }
 
    @Test
    public void testSearchByAuthor() {
        when(bookRepository.findByAuthorContainingIgnoreCase("Author")).thenReturn(Collections.singletonList(book));
        List<BookDTO> result = bookService.searchByAuthor("Author");
        assertEquals(1, result.size());
    }
 
    @Test
    public void testSearchByGenre() {
        when(bookRepository.findByGenreContainingIgnoreCase("Genre")).thenReturn(Collections.singletonList(book));
        List<BookDTO> result = bookService.searchByGenre("Genre");
        assertEquals(1, result.size());
    }
 
    @Test
    public void testIsBookExists() {
        when(bookRepository.existsById(1L)).thenReturn(true);
        assertTrue(bookService.isBookExists(1L));
    }
 
    @Test
    public void testIsBookAvailable() {
        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));
        assertTrue(bookService.isBookAvailable(1L));
    }
}
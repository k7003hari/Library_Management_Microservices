package com.cts;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import com.cts.dto.BookDTO;
import com.cts.model.Book;
import com.cts.repository.BookRepository;
import com.cts.service.BookService;
import com.cts.service.BookServiceImpl;
 
public class BookServiceApplicationTests {
 
    private final BookRepository bookRepository = mock(BookRepository.class);
    private final BookService bookService = new BookServiceImpl(bookRepository);
 
    @Test
    public void testAddBook() {
        BookDTO dto = BookDTO.builder().title("Sample").author("Author").genre("Fiction").isbn("12345").yearPublished(2020).availableCopies(3).build();
        Book book = Book.builder().bookId(1L).title(dto.getTitle()).author(dto.getAuthor()).genre(dto.getGenre()).isbn(dto.getIsbn()).yearPublished(dto.getYearPublished()).availableCopies(dto.getAvailableCopies()).build();
        when(bookRepository.save(Mockito.any())).thenReturn(book);
        BookDTO result = bookService.addBook(dto);
        assertNotNull(result);
        assertEquals("Sample", result.getTitle());
    }
 
    @Test
    public void testGetBookById_NotFound() {
        when(bookRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> bookService.getBookById(1L));
    }
}
 
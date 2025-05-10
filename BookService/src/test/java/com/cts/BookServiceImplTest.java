package com.cts;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.cts.dto.BookDTO;
import com.cts.exception.BookNotFoundException;
import com.cts.model.Book;
import com.cts.repository.BookRepository;
import com.cts.service.BookServiceImpl;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class BookServiceImplTest {

	@Mock
	private BookRepository bookRepository;

	@InjectMocks
	private BookServiceImpl bookService;

	private AutoCloseable closeable;

	private Book book;
	private BookDTO bookDTO;

	@BeforeAll
	void setupAll() {
		System.out.println("Starting BookServiceImpl tests...");
	}

	@BeforeEach
	void setup() {
		closeable = MockitoAnnotations.openMocks(this);

		book = new Book();
		book.setBookId(1L);
		book.setTitle("Test Book");
		book.setAuthor("Author");
		book.setGenre("Fiction");
		book.setIsbn("ISBN001");
		book.setAvailableCopies(5);
		book.setYearPublished(2020);

		bookDTO = new BookDTO();
		bookDTO.setTitle("Test Book");
		bookDTO.setAuthor("Author");
		bookDTO.setGenre("Fiction");
		bookDTO.setIsbn("ISBN001");
		bookDTO.setAvailableCopies(5);
		bookDTO.setYearPublished(2020);
	}

	@Test
	void testAddBookSuccess() {
		when(bookRepository.existByIsbn(bookDTO.getIsbn())).thenReturn(false);
		when(bookRepository.save(any(Book.class))).thenReturn(book);

		BookDTO saved = bookService.addBook(bookDTO);
		assertEquals(book.getTitle(), saved.getTitle());
		verify(bookRepository, times(1)).save(any(Book.class));
	}

	@Test
	void testGetBookByIdSuccess() {
		when(bookRepository.findById(1L)).thenReturn(Optional.of(book));

		BookDTO found = bookService.getBookById(1L);
		assertEquals("Test Book", found.getTitle());
	}

	@Test
	void testGetBookByIdNotFound() {
		when(bookRepository.findById(2L)).thenReturn(Optional.empty());

		assertThrows(BookNotFoundException.class, () -> bookService.getBookById(2L));
	}

	@Test
	void testIsBookExistTrue() {
		when(bookRepository.existsById(1L)).thenReturn(true);
		assertTrue(bookService.isBookExist(1L));
	}

	@Test
	void testIsBookAvailableTrue() {
		when(bookRepository.findById(1L)).thenReturn(Optional.of(book));
		assertTrue(bookService.isBookAvailable(1L));
	}

	@AfterEach
	void tearDown() throws Exception {
		closeable.close();
	}

	@AfterAll
	void tearDownAll() {
		System.out.println("Finished BookServiceImpl tests.");
	}
}
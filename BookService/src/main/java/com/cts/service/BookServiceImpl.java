package com.cts.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.cts.exception.DuplicateResourceException;
import com.cts.dto.BookDTO;
import com.cts.exception.BookNotFoundException;
import com.cts.model.Book;
import com.cts.repository.BookRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class BookServiceImpl implements BookService {

	private BookRepository bookRepository;

	@Override
	public BookDTO addBook(BookDTO bookDTO) {
		if (bookRepository.existsByIsbn(bookDTO.getIsbn())) {
			throw new DuplicateResourceException("Book with ISBN already exists: " + bookDTO.getIsbn());
		}
		Book book = convertToEntity(bookDTO);
		return convertToDTO(bookRepository.save(book));
	}

	@Override
	public List<BookDTO> getAllBooks() {
		return bookRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
	}

	@Override
	public BookDTO getBookById(Long id) {
		Book book = bookRepository.findById(id)
				.orElseThrow(() -> new BookNotFoundException("Book not found with id: " + id));
		return convertToDTO(book);
	}

	@Override
	public BookDTO updateBook(Long id, BookDTO bookDTO) {
		Book book = bookRepository.findById(id)
				.orElseThrow(() -> new BookNotFoundException("Book not found with id: " + id));
		book.setTitle(bookDTO.getTitle());
		book.setAuthor(bookDTO.getAuthor());
		book.setGenre(bookDTO.getGenre());
		book.setIsbn(bookDTO.getIsbn());
		book.setYearPublished(bookDTO.getYearPublished());
		book.setAvailableCopies(bookDTO.getAvailableCopies());
		return convertToDTO(bookRepository.save(book));
	}

	@Override
	public void deleteBook(Long id) {
		Book book = bookRepository.findById(id)
				.orElseThrow(() -> new BookNotFoundException("Book not found with id: " + id));
		bookRepository.delete(book);
	}

	@Override
	public List<BookDTO> searchByTitle(String title) {
		return bookRepository.findByTitleContainingIgnoreCase(title).stream().map(this::convertToDTO)
				.collect(Collectors.toList());
	}

	@Override
	public List<BookDTO> searchByAuthor(String author) {
		return bookRepository.findByAuthorContainingIgnoreCase(author).stream().map(this::convertToDTO)
				.collect(Collectors.toList());
	}

	@Override
	public List<BookDTO> searchByGenre(String genre) {
		return bookRepository.findByGenreContainingIgnoreCase(genre).stream().map(this::convertToDTO)
				.collect(Collectors.toList());
	}

	@Override
	public boolean isBookExists(Long id) {
		return bookRepository.existsById(id);
	}

	@Override
	public boolean isBookAvailable(Long id) {
		Book book = bookRepository.findById(id)
				.orElseThrow(() -> new BookNotFoundException("Book not found with id: " + id));
		return book.getAvailableCopies() > 0;
	}

	private Book convertToEntity(BookDTO dto) {
		return new Book(dto.getTitle(), dto.getAuthor(), dto.getGenre(), dto.getIsbn(), dto.getYearPublished(),
				dto.getAvailableCopies());
	}

	private BookDTO convertToDTO(Book book) {
		return new BookDTO(book.getBookId(), book.getTitle(), book.getAuthor(), book.getGenre(), book.getIsbn(),
				book.getYearPublished(), book.getAvailableCopies());
	}

}
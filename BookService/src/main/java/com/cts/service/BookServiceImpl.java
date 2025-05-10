package com.cts.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.cts.dto.BookDTO;
import com.cts.exception.BookNotFoundException;
import com.cts.exception.BookUnavailableException;
import com.cts.exception.DuplicateBookException;
import com.cts.model.Book;
import com.cts.repository.BookRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class BookServiceImpl implements BookService {

	private final BookRepository bookRepository;

	@Override
	public BookDTO addBook(BookDTO bookDTO) {
		log.info("Adding new book: {}", bookDTO.getTitle());
		if (bookRepository.existByIsbn(bookDTO.getIsbn())) {
			throw new DuplicateBookException("Book with ISBN already exists: " + bookDTO.getIsbn());
		}

		Book book = new Book();
		BeanUtils.copyProperties(bookDTO, book);
		Book saved = bookRepository.save(book);
		BookDTO result = new BookDTO();
		BeanUtils.copyProperties(saved, result);
		return result;
	}

	@Override
	public BookDTO updateBook(Long bookId, BookDTO bookDTO) {
		log.info("Updating book with ID: {}", bookId);
		Book book = bookRepository.findById(bookId)
				.orElseThrow(() -> new BookNotFoundException("Book not found with ID " + bookId));

		BeanUtils.copyProperties(bookDTO, book, "id");
		Book updated = bookRepository.save(book);
		BookDTO result = new BookDTO();
		BeanUtils.copyProperties(updated, result);
		return result;
	}

	@Override
	public void deleteBook(Long bookId) {
		log.info("Deleting book with ID: {}", bookId);
		if (!bookRepository.existsById(bookId)) {
			throw new BookNotFoundException("Cannot delete. Book not found with ID " + bookId);
		}
		bookRepository.deleteById(bookId);
	}

	@Override
	public BookDTO getBookById(Long bookId) {
		log.info("Fetching book with ID: {}", bookId);
		Book book = bookRepository.findById(bookId)
				.orElseThrow(() -> new BookNotFoundException("Book not found with ID " + bookId));
		BookDTO result = new BookDTO();
		BeanUtils.copyProperties(book, result);
		return result;
	}

	@Override
	public List<BookDTO> getAllBooks() {
		log.info("Fetching all books");
		return bookRepository.findAll().stream().map(book -> {
			BookDTO dto = new BookDTO();
			BeanUtils.copyProperties(book, dto);
			return dto;
		}).collect(Collectors.toList());
	}

	@Override
	public List<BookDTO> searchByTitle(String title) {
		log.info("Searching books by title: {}", title);
		return bookRepository.findByTitleContainingIgnoreCase(title).stream().map(book -> {
			BookDTO dto = new BookDTO();
			BeanUtils.copyProperties(book, dto);
			return dto;
		}).collect(Collectors.toList());
	}

	@Override
	public List<BookDTO> searchByAuthor(String author) {
		log.info("Searching books by author: {}", author);
		return bookRepository.findByAuthorContainingIgnoreCase(author).stream().map(book -> {
			BookDTO dto = new BookDTO();
			BeanUtils.copyProperties(book, dto);
			return dto;
		}).collect(Collectors.toList());
	}

	@Override
	public List<BookDTO> searchByGenre(String genre) {
		log.info("Searching books by genre: {}", genre);
		return bookRepository.findByGenreContainingIgnoreCase(genre).stream().map(book -> {
			BookDTO dto = new BookDTO();
			BeanUtils.copyProperties(book, dto);
			return dto;
		}).collect(Collectors.toList());
	}

	@Override
	public boolean isBookExist(Long bookId) {
		log.info("Checking if book exists with ID: {}", bookId);
		return bookRepository.existsById(bookId);
	}

	@Override
	public boolean isBookAvailable(Long bookId) {
		log.info("Checking if book is available with ID: {}", bookId);
		Book book = bookRepository.findById(bookId)
				.orElseThrow(() -> new BookNotFoundException("Book not found with ID " + bookId));

		if (book.getAvailableCopies() <= 0) {
			throw new BookUnavailableException("Book is not currently available.");
		}
		return true;
	}
}
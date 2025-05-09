package com.cts.service;

import java.util.List;

import com.cts.dto.BookDTO;

public interface BookService {
	BookDTO addBook(BookDTO bookDTO);

	List<BookDTO> getAllBooks();

	BookDTO getBookById(Long id);

	BookDTO updateBook(Long id, BookDTO bookDTO);

	void deleteBook(Long id);

	List<BookDTO> searchByTitle(String title);

	List<BookDTO> searchByAuthor(String author);

	List<BookDTO> searchByGenre(String genre);

	boolean isBookExists(Long id);

	boolean isBookAvailable(Long id);
}
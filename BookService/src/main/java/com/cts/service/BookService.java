package com.cts.service;

import java.util.List;

import com.cts.dto.BookDTO;

public interface BookService {
	BookDTO addBook(BookDTO bookDTO);

	BookDTO updateBook(Long bookId, BookDTO bookDTO);

	void deleteBook(Long bookId);

	BookDTO getBookById(Long bookId);

	List<BookDTO> getAllBooks();

	List<BookDTO> searchByTitle(String title);

	List<BookDTO> searchByAuthor(String author);

	List<BookDTO> searchByGenre(String genre);

	boolean isBookExist(Long id);

	boolean isBookAvailable(Long id);
}
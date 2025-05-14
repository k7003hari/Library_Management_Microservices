package com.cts.service;

import java.util.List;

import com.cts.dto.BookDTO;

public interface BookService {
	BookDTO addBook(BookDTO bookDTO);

	BookDTO updateBook(Long id, BookDTO bookDTO);

	void deleteBook(Long id);

	BookDTO getBook(Long id);

	List<BookDTO> searchByTitle(String title);

	List<BookDTO> searchByAuthor(String author);

	List<BookDTO> searchByGenre(String genre);

	BookDTO getBookForMember(Long bookId, String memberEmail);
}
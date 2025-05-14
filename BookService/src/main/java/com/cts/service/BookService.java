package com.cts.service;

import java.util.List;

import com.cts.model.Book;

public interface BookService {
	Book addBook(Book book);

	Book updateBook(Long id, Book book);

	void deleteBook(Long id);

	Book getBook(Long id);

	List<Book> searchByTitle(String title);

	List<Book> searchByAuthor(String author);

	List<Book> searchByGenre(String genre);

	Book getBookForMember(Long bookId, String memberEmail);
	
	 void updateBookCopies(Long id, int availableCopies);
	
	
}
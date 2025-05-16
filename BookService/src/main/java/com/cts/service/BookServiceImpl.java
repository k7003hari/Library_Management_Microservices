package com.cts.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.cts.dto.MemberDTO;
import com.cts.exception.BookNotFoundException;
import com.cts.exception.UnauthorizedAccessException;
import com.cts.feign.MemberClient;
import com.cts.model.Book;
import com.cts.repository.BookRepository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Slf4j
public class BookServiceImpl implements BookService {

	private final BookRepository repository;
	private final MemberClient memberClient;

	@Override
	public Book addBook(Book book) {
		log.info("Attempting to add new book with title: {}", book.getTitle());
		Book saved = repository.save(book);
		log.info("Book added successfully with ID: {}", saved.getBookId());
		return saved;
	}
	
	@Override
	public List<Book> getAllBooks() {
	    log.debug("Fetching all books");
	    return repository.findAll();
	}


	@Override
	public Book updateBook(Long id, Book book) {
		log.info("Attempting to update book with ID: {}", id);
		Book existingBook = repository.findById(id).orElseThrow(() -> {
			log.error("Update failed: Book not found with ID: {}", id);
			return new BookNotFoundException("Book not found with ID: " + id);
		});
		existingBook.setTitle(book.getTitle());
		existingBook.setAuthor(book.getAuthor());
		existingBook.setGenre(book.getGenre());
		existingBook.setIsbn(book.getIsbn());
		existingBook.setYearPublished(book.getYearPublished());
		existingBook.setAvailableCopies(book.getAvailableCopies());
		repository.save(existingBook);
		log.info("Book updated successfully with ID: {}", id);
		return existingBook;
	}

	@Override
	public void deleteBook(Long id) {
		log.info("Attempting to delete book with ID: {}", id);
		if (!repository.existsById(id)) {
			log.error("Delete failed: Book not found with ID: {}", id);
			throw new BookNotFoundException("Book not found with ID: " + id);
		}
		repository.deleteById(id);
		log.info("Book deleted successfully with ID: {}", id);
	}

	@Override
	public Book getBookById(Long id) {
		log.debug("Fetching book with ID: {}", id);
		return repository.findById(id).orElseThrow(() -> {
			log.error("Book not found with ID: {}", id);
			return new BookNotFoundException("Book not found with ID: " + id);
		});
	}
	


	@Override
	public List<Book> searchByTitle(String title) {
		log.debug("Searching books with title containing: {}", title);
		return repository.findByTitleContainingIgnoreCase(title).stream().collect(Collectors.toList());
	}

	@Override
	public List<Book> searchByAuthor(String author) {
		log.debug("Searching books with author containing: {}", author);
		return repository.findByAuthorContainingIgnoreCase(author).stream().collect(Collectors.toList());
	}

	@Override
	public List<Book> searchByGenre(String genre) {
		log.debug("Searching books with genre containing: {}", genre);
		return repository.findByGenreContainingIgnoreCase(genre).stream().collect(Collectors.toList());
	}

	@Override
	public Book getBookForMember(Long bookId, String memberEmail) {
		log.debug("Fetching book with ID: {} for member with email: {}", bookId, memberEmail);
		MemberDTO member = memberClient.getMemberByEmail(memberEmail);
		if (member == null || !"ACTIVE".equalsIgnoreCase(member.getMembershipStatus())) {
			log.error("Unauthorized access attempt by member with email: {}", memberEmail);
			throw new UnauthorizedAccessException("Member is not authorized to access books.");
		}
		return repository.findById(bookId).orElseThrow(() -> {
			log.error("Book not found with ID: {}", bookId);
			return new BookNotFoundException("Book not found with ID: " + bookId);
		});
	}

	@Override
	public void updateBookCopies(Long id, int availableCopies) {
		log.info("Attempting to update available copies for book with ID: {}", id);
		Book book = repository.findById(id).orElseThrow(() -> {
			log.error("Update failed: Book not found with ID: {}", id);
			return new BookNotFoundException("Book not found with ID: " + id);
		});
		book.setAvailableCopies(availableCopies);
		repository.save(book);
		log.info("Updated available copies for book with ID: {}", id);
	}

}

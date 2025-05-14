package com.cts.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.cts.dto.BookDTO;
import com.cts.dto.MemberDTO;
import com.cts.exception.BookNotFoundException;
import com.cts.exception.UnauthorizedAccessException;
import com.cts.feign.MemberClient;
import com.cts.model.Book;
import com.cts.repository.BookRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class BookServiceImpl implements BookService {
 
    private final BookRepository repository;
    private final MemberClient memberClient;
 
    @Override
    public BookDTO addBook(BookDTO dto) {
        Book book = mapToEntity(dto);
        Book saved = repository.save(book);
log.info("Book added with ID {}", saved.getBookId());
        return mapToDTO(saved);
    }
 
    @Override
    public BookDTO updateBook(Long id, BookDTO dto) {
        Book book = repository.findById(id)
                .orElseThrow(() -> new BookNotFoundException("Book not found with ID: " + id));
        book.setTitle(dto.getTitle());
        book.setAuthor(dto.getAuthor());
        book.setGenre(dto.getGenre());
        book.setIsbn(dto.getIsbn());
        book.setYearPublished(dto.getYearPublished());
        book.setAvailableCopies(dto.getAvailableCopies());
        repository.save(book);
log.info("Book updated with ID {}", id);
        return mapToDTO(book);
    }
 
    @Override
    public void deleteBook(Long id) {
        if (!repository.existsById(id)) {
            throw new BookNotFoundException("Book not found with ID: " + id);
        }
        repository.deleteById(id);
log.info("Book deleted with ID {}", id);
    }
 
    @Override
    public BookDTO getBook(Long id) {
        return repository.findById(id)
                .map(this::mapToDTO)
                .orElseThrow(() -> new BookNotFoundException("Book not found with ID: " + id));
    }
 
    @Override
    public List<BookDTO> searchByTitle(String title) {
        return repository.findByTitleContainingIgnoreCase(title).stream()
                .map(this::mapToDTO).collect(Collectors.toList());
    }
 
    @Override
    public List<BookDTO> searchByAuthor(String author) {
        return repository.findByAuthorContainingIgnoreCase(author).stream()
                .map(this::mapToDTO).collect(Collectors.toList());
    }
 
    @Override
    public List<BookDTO> searchByGenre(String genre) {
        return repository.findByGenreContainingIgnoreCase(genre).stream()
                .map(this::mapToDTO).collect(Collectors.toList());
    }
 
    @Override
    public BookDTO getBookForMember(Long bookId, String memberEmail) {
        MemberDTO member = memberClient.getMemberByEmail(memberEmail);
        if (member == null || !"ACTIVE".equalsIgnoreCase(member.getMembershipStatus())) {
            throw new UnauthorizedAccessException("Member is not authorized to access books.");
        }
        Book book = repository.findById(bookId)
                .orElseThrow(() -> new BookNotFoundException("Book not found with ID: " + bookId));
        return mapToDTO(book);
    }
 
    private Book mapToEntity(BookDTO dto) {
        return Book.builder()
                .title(dto.getTitle())
                .author(dto.getAuthor())
                .genre(dto.getGenre())
                .isbn(dto.getIsbn())
                .yearPublished(dto.getYearPublished())
                .availableCopies(dto.getAvailableCopies())
                .build();
    }
 
    private BookDTO mapToDTO(Book book) {
        return BookDTO.builder()
                .bookId(book.getBookId())
                .title(book.getTitle())
                .author(book.getAuthor())
                .genre(book.getGenre())
                .isbn(book.getIsbn())
                .yearPublished(book.getYearPublished())
                .availableCopies(book.getAvailableCopies())
                .build();
    }
}
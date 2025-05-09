package com.cts.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cts.model.Book;

public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByTitleContainingIgnoreCase(String title);
    List<Book> findByAuthorContainingIgnoreCase(String author);
    List<Book> findByGenreContainingIgnoreCase(String genre);
    boolean existsByIsbn(String sibn);
}
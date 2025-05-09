package com.cts.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Book {
	public Book(String title2, String author2, String genre2, String isbn2, int yearPublished2, int availableCopies2) {
	}
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long bookId;
	private String title;
	private String author;
	private String genre;
	private String isbn;
	private int yearPublished;
	private int availableCopies;
}

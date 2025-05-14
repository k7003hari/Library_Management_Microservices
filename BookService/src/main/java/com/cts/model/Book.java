package com.cts.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
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

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long bookId;

	@NotBlank(message = "Title is required")
	private String title;

	@NotBlank(message = "Author is required")
	private String author;

	@NotBlank(message = "Genre is required")
	private String genre;

	@Pattern(regexp = "\\d{13}", message = "ISBN must be 13 digits")
	private String isbn;

	@Min(value = 1500, message = "Year must be valid")
	@Max(value = 2100, message = "Year must be valid")
	private Integer yearPublished;

	@Min(value = 0, message = "Available copies must be 0 or more")
	private Integer availableCopies;
}
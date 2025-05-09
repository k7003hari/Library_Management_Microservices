package com.cts.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookDTO {

	public BookDTO(Long bookId, String title2, String author2, String genre2, String isbn2, int yearPublished2,
			int availableCopies2) {
	}

	@NotBlank(message = "Title is required")
	private String title;

	@NotBlank(message = "Author is required")
	private String author;

	@NotBlank(message = "Genre is required")
	private String genre;

	@NotBlank(message = "ISBN is required")
	private String isbn;

	@Min(value = 1000, message = "Year must be a valid 4-digit number")
	private int yearPublished;

	@Min(value = 0, message = "Available copies cannot be negative")
	private int availableCopies;
}
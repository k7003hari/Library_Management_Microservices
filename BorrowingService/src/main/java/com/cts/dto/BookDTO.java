package com.cts.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookDTO {
	private Long bookId;
	private String title;
	private String author;
	private String genre;
	private String isbn;
	private int yearPublished;
	private int availableCopies;
}
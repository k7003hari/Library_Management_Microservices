package com.cts.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BorrowingDTO {
	private Long id;

	@NotNull(message = "Member ID cannot be null")
	private Long memberId;

	@NotNull(message = "Book ID cannot be null")
	private Long bookId;

	private LocalDate borrowDate;
	private LocalDate returnDate;
	private boolean returned;
}
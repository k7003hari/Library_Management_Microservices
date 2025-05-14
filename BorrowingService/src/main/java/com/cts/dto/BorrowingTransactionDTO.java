package com.cts.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BorrowingTransactionDTO {
 
   
	private Long transactionId;
 
    @NotNull(message = "Book ID cannot be null")
    private Long bookId;
 
    @NotNull(message = "Member ID cannot be null")
    private Long memberId;
 
    private LocalDate borrowDate;
    private LocalDate returnDate;
 
    @NotNull(message = "Status is required")
    private String status; // BORROWED or RETURNED
    
    public enum Status{
    	BORROWED,
    	RETURNED
    }
}
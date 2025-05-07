package com.cts.dto;

import java.time.LocalDate;

import lombok.Data;
 
@Data
public class BorrowingTransactionDTO {
    private Long transactionId;
    private Long bookId;
    private Long memberId;
    private LocalDate borrowDate;
    private LocalDate returnDate;
    private String status;
}
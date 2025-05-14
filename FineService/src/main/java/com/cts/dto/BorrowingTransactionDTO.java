package com.cts.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BorrowingTransactionDTO {

    private Long transactionId;
    private Long bookId;
    private Long memberId;
    private LocalDate borrowDate;
    private LocalDate returnDate;

    private Status status;

    public enum Status {
        BORROWED,
        RETURNED
    }
}
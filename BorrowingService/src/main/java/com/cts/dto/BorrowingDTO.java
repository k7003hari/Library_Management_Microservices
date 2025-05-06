package com.cts.dto;

import lombok.*;

import java.time.LocalDate;
 
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BorrowingDTO {
 
    private Long transactionId;
    private Long bookId;
    private Long memberId;
    private LocalDate borrowDate;
    private LocalDate returnDate;
    private String status;
}
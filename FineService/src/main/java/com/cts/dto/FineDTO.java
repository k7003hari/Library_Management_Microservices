package com.cts.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
 
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FineDTO {
 
    private Long fineId;
 
    @NotNull(message = "Member ID cannot be null")
    private Long memberId;
 
    @NotNull(message = "Book ID cannot be null")
    private Long bookId;
 
    @NotNull(message = "Amount cannot be null")
    private Double amount;
 
    private LocalDate issuedDate;
    private boolean paid;
}
 
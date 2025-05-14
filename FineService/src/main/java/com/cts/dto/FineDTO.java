package com.cts.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FineDTO {
    private Long fineId;
    private MemberDTO memberId;
    private BigDecimal amount;
    private String status; // Must be named "status"
    private LocalDate transactionDate;
}
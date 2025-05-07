package com.cts.service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ctc.exception.FineNotFoundException;
import com.cts.dto.BorrowingTransactionDTO;
import com.cts.feign.BorrowingClient;
import com.cts.model.Fine;
import com.cts.model.FineStatus;
import com.cts.repository.FineRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FineServiceImpl implements FineService {
 
    private final FineRepository fineRepository;
    private final BorrowingClient borrowingClient;
 
    private static final double DAILY_FINE = 2.0;
 
    @Override
    public Fine calculateFine(Long transactionId) {
        BorrowingTransactionDTO transaction = borrowingClient.getTransactionById(transactionId);
        LocalDate dueDate = transaction.getBorrowDate().plusDays(14);
        LocalDate actualReturnDate = transaction.getReturnDate();
 
        if (actualReturnDate == null || actualReturnDate.isBefore(dueDate)) {
            return null;
        }
 
        long overdueDays = ChronoUnit.DAYS.between(dueDate, actualReturnDate);
        double fineAmount = overdueDays * DAILY_FINE;
 
        Fine fine = Fine.builder()
                .memberId(transaction.getMemberId())
                .transactionId(transaction.getTransactionId())
                .amount(fineAmount)
                .status(FineStatus.PENDING)
                .transactionDate(LocalDate.now())
                .build();
 
        return fineRepository.save(fine);
    }
 
    @Override
    public Fine payFine(Long fineId) {
        Fine fine = fineRepository.findById(fineId)
                .orElseThrow(() -> new FineNotFoundException("Fine with ID " + fineId + " not found"));
 
        fine.setStatus(FineStatus.PAID);
        return fineRepository.save(fine);
    }
 
    @Override
    public List<Fine> getFinesByMemberId(Long memberId) {
        return fineRepository.findByMemberId(memberId);
    }
}
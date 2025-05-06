package com.cts.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.cts.dto.BorrowingDTO;
import com.cts.exception.BorrowingNotFoundException;
import com.cts.model.BorrowingTransaction;
import com.cts.repository.BorrowingRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BorrowingServiceImpl implements BorrowingService {
 
    private final BorrowingRepository borrowingRepository;
 
    @Override
    public BorrowingTransaction borrowBook(BorrowingDTO dto) {
        BorrowingTransaction transaction = BorrowingTransaction.builder()
                .bookId(dto.getBookId())
                .memberId(dto.getMemberId())
                .borrowDate(LocalDate.now())
                .status("Borrowed")
                .build();
        return borrowingRepository.save(transaction);
    }
 
    @Override
    public BorrowingTransaction returnBook(Long transactionId) {
        BorrowingTransaction transaction = borrowingRepository.findById(transactionId)
                .orElseThrow(() -> new BorrowingNotFoundException("Transaction not found with ID: " + transactionId));
 
        transaction.setReturnDate(LocalDate.now());
        transaction.setStatus("Returned");
 
        return borrowingRepository.save(transaction);
    }
 
    @Override
    public BorrowingTransaction getTransactionById(Long transactionId) {
        return borrowingRepository.findById(transactionId)
                .orElseThrow(() -> new BorrowingNotFoundException("Transaction not found with ID: " + transactionId));
    }
 
    @Override
    public List<BorrowingTransaction> getTransactionsByMemberId(Long memberId) {
        return borrowingRepository.findByMemberId(memberId);
    }
 
    @Override
    public List<BorrowingTransaction> getTransactionsByBookId(Long bookId) {
        return borrowingRepository.findByBookId(bookId);
    }
 
    @Override
    public List<BorrowingTransaction> getAllTransactions() {
        return borrowingRepository.findAll();
    }
 
    @Override
    public void deleteTransaction(Long transactionId) {
        if (!borrowingRepository.existsById(transactionId)) {
            throw new BorrowingNotFoundException("Transaction not found with ID: " + transactionId);
        }
        borrowingRepository.deleteById(transactionId);
    }
}
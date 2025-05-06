package com.cts.service;

import java.util.List;

import com.cts.dto.BorrowingDTO;
import com.cts.model.BorrowingTransaction;

public interface BorrowingService {
	 
    BorrowingTransaction borrowBook(BorrowingDTO borrowingDTO);
    BorrowingTransaction returnBook(Long transactionId);
    BorrowingTransaction getTransactionById(Long transactionId);
    List<BorrowingTransaction> getTransactionsByMemberId(Long memberId);
    List<BorrowingTransaction> getTransactionsByBookId(Long bookId);
    List<BorrowingTransaction> getAllTransactions();
    void deleteTransaction(Long transactionId);
}
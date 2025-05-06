package com.cts.service;

import java.util.List;

import com.cts.model.BorrowingTransaction;

public interface BorrowingService {
    BorrowingTransaction borrowBook(Long memberId, Long bookId);
    BorrowingTransaction returnBook(Long transactionId);
    BorrowingTransaction getTransactionById(Long transactionId);
    List<BorrowingTransaction> getAllTransactions();
    List<BorrowingTransaction> getTransactionsByMemberId(Long memberId);
    List<BorrowingTransaction> getBorrowedBooksByBookId(Long bookId);
}

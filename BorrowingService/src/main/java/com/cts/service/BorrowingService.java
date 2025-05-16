package com.cts.service;

import java.util.List;

import com.cts.dto.BorrowingTransactionDTO;
import com.cts.model.BorrowingTransaction;

public interface BorrowingService {

	BorrowingTransaction borrowBook(BorrowingTransactionDTO transactionDTO);

	BorrowingTransaction returnBook(Long memberId, Long bookId);	

	List<BorrowingTransaction> getMemberBorrowedBooks(Long memberId);
}
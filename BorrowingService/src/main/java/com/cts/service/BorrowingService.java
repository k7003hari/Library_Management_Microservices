package com.cts.service;

import java.util.List;

import com.cts.model.BorrowingTransaction;

public interface BorrowingService {

	BorrowingTransaction borrowBook(Long memberId, Long bookId);

	BorrowingTransaction returnBook(Long memberId, Long bookId);
	

	List<BorrowingTransaction> getMemberBorrowedBooks(Long memberId);
}
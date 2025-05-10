package com.cts.service;

import java.util.List;

import com.cts.dto.BorrowingDTO;

public interface BorrowingService {
	BorrowingDTO borrowBook(BorrowingDTO borrowingDTO);

	BorrowingDTO returnBook(Long transactionId);

	List<BorrowingDTO> getBorrowingsByMember(Long memberId);

	boolean isBookBorrowed(Long bookId);

	BorrowingDTO getBorrowingByTransactionId(Long transactionId);

	List<BorrowingDTO> getAllBorrowings();
}
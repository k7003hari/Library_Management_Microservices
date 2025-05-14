package com.cts.service;

import java.util.List;

import com.cts.dto.BorrowingTransactionDTO;

public interface BorrowingService {

	BorrowingTransactionDTO borrowBook(Long memberId, Long bookId);

	BorrowingTransactionDTO returnBook(Long memberId, Long bookId);

	List<BorrowingTransactionDTO> getMemberBorrowedBooks(Long memberId);
}
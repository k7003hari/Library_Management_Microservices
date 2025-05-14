package com.cts.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cts.dto.BorrowingTransactionDTO;
import com.cts.service.BorrowingService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/borrowings")
@RequiredArgsConstructor
public class BorrowingController {

	private final BorrowingService borrowingService;

	// Borrow a book
	@PostMapping("/borrow")
	public BorrowingTransactionDTO borrowBook(@RequestParam Long memberId, @RequestParam Long bookId) {
		return borrowingService.borrowBook(memberId, bookId);
	}

	// Return a book
	@PostMapping("/return")
	public BorrowingTransactionDTO returnBook(@RequestParam Long memberId, @RequestParam Long bookId) {
		return borrowingService.returnBook(memberId, bookId);
	}

	// Get all currently borrowed books by a member
	@GetMapping("/member/{memberId}")
	public List<BorrowingTransactionDTO> getBorrowedBooks(@PathVariable Long memberId) {
		return borrowingService.getMemberBorrowedBooks(memberId);
	}
}
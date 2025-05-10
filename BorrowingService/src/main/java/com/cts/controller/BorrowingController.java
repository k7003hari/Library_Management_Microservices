package com.cts.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cts.dto.BorrowingDTO;
import com.cts.service.BorrowingService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/borrowings")
@RequiredArgsConstructor
@Slf4j
public class BorrowingController {

	private final BorrowingService borrowingService;

	@PostMapping("/borrowbook")
	public BorrowingDTO borrowBook(@Valid @RequestBody BorrowingDTO dto) {
		log.info("BorrowingController: borrowing a book");
		return borrowingService.borrowBook(dto);
	}

	@PutMapping("/returnbook/{transactionId}")
	public BorrowingDTO returnBook(@PathVariable Long transactionId) {
		log.info("BorrowingController: returning a book");
		return borrowingService.returnBook(transactionId);
	}

	@GetMapping("/borrowedmember/{memberId}")
	public List<BorrowingDTO> getBorrowingsByMember(@PathVariable Long memberId) {
		log.info("BorrowingController: fetching borrowings for memberId {}", memberId);
		return borrowingService.getBorrowingsByMember(memberId);
	}

	@GetMapping("/bookborrowed/{bookId}")
	public boolean isBookBorrowed(@PathVariable Long bookId) {
		log.info("BorrowingController: checking if bookId {} is borrowed", bookId);
		return borrowingService.isBookBorrowed(bookId);
	}

	@GetMapping("transaction/{transactionId}")
	public BorrowingDTO getByTransactionId(@PathVariable Long transactionId) {
		log.info("BorrowingController: fetching by transactionId {}", transactionId);
		return borrowingService.getBorrowingByTransactionId(transactionId);
	}

	@GetMapping("/getallborrows/all")
	public List<BorrowingDTO> getAllBorrowings() {
		log.info("BorrowingController: fetching all borrowings");
		return borrowingService.getAllBorrowings();
	}
}
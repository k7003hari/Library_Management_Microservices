package com.cts.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cts.dto.BorrowingTransactionDTO;
import com.cts.model.BorrowingTransaction;
import com.cts.service.BorrowingService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/borrowings")
public class BorrowingController {

	private final BorrowingService borrowingService;

	// Borrow a book
	@PostMapping("/borrow")
	public BorrowingTransaction borrowBook(@RequestBody BorrowingTransactionDTO transactionDTO) {
	    return borrowingService.borrowBook(transactionDTO.getMemberId(), transactionDTO.getBookId());
	}

    // Return a book
	@PostMapping("/return")
	public BorrowingTransaction returnBook(@RequestBody BorrowingTransactionDTO transactionDTO) {
	    return borrowingService.returnBook(transactionDTO.getMemberId(), transactionDTO.getBookId());
	}


    // Get all currently borrowed books by a member
    @GetMapping("/{memberId}")
    public List<BorrowingTransaction> getBorrowedBooks(@PathVariable Long memberId) {
        return borrowingService.getMemberBorrowedBooks(memberId);
    }
}

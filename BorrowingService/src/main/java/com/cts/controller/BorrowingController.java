package com.cts.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cts.model.BorrowingTransaction;
import com.cts.service.BorrowingService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/borrowing")
@RequiredArgsConstructor
public class BorrowingController {
    private final BorrowingService borrowingService;
 
    @PostMapping("/borrow")
    public BorrowingTransaction borrowBook(@RequestBody BorrowingTransaction tx) {
        return borrowingService.borrowBook(tx);
    }
 
    @PutMapping("/return/{id}")
    public BorrowingTransaction returnBook(@PathVariable Long id) {
        return borrowingService.returnBook(id);
    }
 
    @GetMapping
    public List<BorrowingTransaction> getAllTransactions() {
        return borrowingService.getAllTransactions();
    }
}

package com.cts.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cts.dto.BorrowingDTO;
import com.cts.model.BorrowingTransaction;
import com.cts.service.BorrowingService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/borrowings")
@RequiredArgsConstructor
public class BorrowingController {
 
    private final BorrowingService borrowingService;
 
    @PostMapping("/borrow")
    public ResponseEntity<BorrowingTransaction> borrowBook(@RequestBody BorrowingDTO dto) {
        return ResponseEntity.ok(borrowingService.borrowBook(dto));
    }
 
    @PutMapping("/return/{id}")
    public ResponseEntity<BorrowingTransaction> returnBook(@PathVariable Long id) {
        return ResponseEntity.ok(borrowingService.returnBook(id));
    }
 
    @GetMapping("/get/{id}")
    public ResponseEntity<BorrowingTransaction> getTransaction(@PathVariable Long id) {
        return ResponseEntity.ok(borrowingService.getTransactionById(id));
    }
 
    @GetMapping("/member/{memberId}")
    public ResponseEntity<List<BorrowingTransaction>> getByMember(@PathVariable Long memberId) {
        return ResponseEntity.ok(borrowingService.getTransactionsByMemberId(memberId));
    }
 
    @GetMapping("/book/{bookId}")
    public ResponseEntity<List<BorrowingTransaction>> getByBook(@PathVariable Long bookId) {
        return ResponseEntity.ok(borrowingService.getTransactionsByBookId(bookId));
    }
 
    @GetMapping
    public ResponseEntity<List<BorrowingTransaction>> getAll() {
        return ResponseEntity.ok(borrowingService.getAllTransactions());
    }
 
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTransaction(@PathVariable Long id) {
        borrowingService.deleteTransaction(id);
        return ResponseEntity.ok("Transaction deleted successfully.");
    }
}
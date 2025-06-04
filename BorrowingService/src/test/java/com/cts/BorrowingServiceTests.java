package com.cts;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.cts.dto.BorrowingTransactionDTO;
import com.cts.model.BorrowingTransaction;
import com.cts.repository.BorrowingRepository;
import com.cts.service.BorrowingService;

@SpringBootTest
class BorrowingServiceTests {
 
    @Autowired
    private BorrowingService borrowingService;
 
    @Autowired
    private BorrowingRepository repository;
 
    private BorrowingTransactionDTO dto;
 
    @BeforeEach
    void init() {
        dto = new BorrowingTransactionDTO();
        dto.setBookId(1L);
        dto.setMemberId(2L);
    }
 
    @Test
    void testBorrowBook() {
        BorrowingTransaction result = borrowingService.borrowBook(dto);
        assertNotNull(result.getId());
        assertEquals(1L, result.getBookId());
        assertEquals(2L, result.getMemberId());
        assertEquals(BorrowingTransaction.Status.BORROWED, result.getStatus());
    }
 
    @Test
    void testReturnBook() {
        BorrowingTransaction borrow = borrowingService.borrowBook(dto);
        BorrowingTransaction returned = borrowingService.returnBook(2L, 1L);
        assertEquals(BorrowingTransaction.Status.RETURNED, returned.getStatus());
        assertNotNull(returned.getReturnDate());
    }
 
    @Test
    void testGetBorrowedBooksByMember() {
        borrowingService.borrowBook(dto);
        List<BorrowingTransaction> list = borrowingService.getMemberBorrowedBooks(2L);
        assertFalse(list.isEmpty());
        assertEquals(2L, list.get(0).getMemberId());
        assertEquals(BorrowingTransaction.Status.BORROWED, list.get(0).getStatus());
    }
}
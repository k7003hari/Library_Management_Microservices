package com.cts;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.cts.dto.BorrowingTransactionDTO;
import com.cts.service.BorrowingService;
@SpringBootTest
class BorrowingServiceTests {
 
    @Autowired
    private BorrowingService borrowingService;
 
    @Test
    void testBorrowBook() {
        Long memberId = 1L;
        Long bookId = 1L;
        BorrowingTransactionDTO dto = borrowingService.borrowBook(memberId, bookId);
        assertNotNull(dto);
        assertEquals(dto.getStatus(), BorrowingTransactionDTO.Status.BORROWED);
    }
}
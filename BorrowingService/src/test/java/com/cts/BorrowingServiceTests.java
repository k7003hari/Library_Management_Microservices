//package com.cts;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertFalse;
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//
//import java.time.LocalDate;
//import java.util.List;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.ActiveProfiles;
//
//import com.cts.dto.BorrowingTransactionDTO;
//import com.cts.model.BorrowingTransaction;
//import com.cts.service.BorrowingService;
//
//@SpringBootTest
//@ActiveProfiles("test") // assumes your test profile uses MySQL
//class BorrowingServiceTests {
//
//    @Autowired
//    private BorrowingService borrowingService;
//
//    private BorrowingTransaction sampleTransaction;
//
//    @BeforeEach
//    void init() {
//        sampleTransaction = new BorrowingTransaction();
//        sampleTransaction.setMemberId(1L); // Add this line to set the memberId
//        sampleTransaction.setBookId(1L);
//        sampleTransaction.setBorrowDate(LocalDate.now());
//        sampleTransaction.setStatus(BorrowingTransaction.Status.BORROWED);
//    }
//
//
//    @Test
//    void testBorrowBook() {
//        BorrowingTransactionDTO transactionDTO = new BorrowingTransactionDTO();
//        transactionDTO.setMemberId(1L);
//        transactionDTO.setBookId(2L);
//        transactionDTO.setBorrowDate(LocalDate.now());
//
//        BorrowingTransaction saved = borrowingService.borrowBook(transactionDTO);
//        assertNotNull(saved.getId());
//        assertEquals(1L, saved.getMemberId());
//        assertEquals(1L, saved.getBookId());
//        assertEquals(LocalDate.now(), saved.getBorrowDate());
//        assertEquals(BorrowingTransaction.Status.BORROWED, saved.getStatus());
//    }
//
//    @Test
//    void testReturnBook() {
//        BorrowingTransaction saved = borrowingService.borrowBook(new BorrowingTransactionDTO(1L, 1L, null, LocalDate.now(), null, "BORROWED"));
//        BorrowingTransaction returned = borrowingService.returnBook(1L, 1L);
//        assertEquals(BorrowingTransaction.Status.RETURNED, returned.getStatus());
//    }
//
//    @Test
//    void testGetMemberBorrowedBooks() {
//        borrowingService.borrowBook(new BorrowingTransactionDTO(1L, 1L, null, LocalDate.now(), null,"BORROWED"));
//        List<BorrowingTransaction> borrowedBooks = borrowingService.getMemberBorrowedBooks(1L);
//        assertFalse(borrowedBooks.isEmpty());
//        assertEquals(1L, borrowedBooks.get(0).getMemberId());
//    }
//}

package com.cts;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import com.cts.exception.TransactionNotFoundException;
import com.cts.model.BorrowingTransaction;
import com.cts.repository.BorrowingTransactionRepository;
import com.cts.service.BorrowingService;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class BorrowingServiceApplicationTests {
 
    @Mock
    private BorrowingTransactionRepository repository;
 
    @InjectMocks
    private BorrowingService service;
 
    @Test
    public void testBorrowBook() {
        BorrowingTransaction tx = new BorrowingTransaction(null, 1L, 2L, null, null, "Borrowed");
        when(repository.save(any(BorrowingTransaction.class))).thenReturn(tx);
        BorrowingTransaction result = service.borrowBook(tx);
        assertEquals("Borrowed", result.getStatus());
    }
 
    @Test
    public void testReturnBook_NotFound() {
        when(repository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(TransactionNotFoundException.class, () -> service.returnBook(1L));
    }
}
 
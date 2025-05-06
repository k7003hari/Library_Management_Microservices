package com.cts.service;

import java.awt.print.Book;
import java.lang.reflect.Member;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.exception.TransactionNotFoundException;
import com.cts.feignClient.BookClient;
import com.cts.feignClient.MemberClient;
import com.cts.model.BorrowingTransaction;
import com.cts.repository.BorrowingTransactionRepository;

@Service
public class BorrowingServiceImpl implements BorrowingService {
 
    @Autowired
    private BorrowingTransactionRepository transactionRepository;
 
    @Autowired
    private BookClient bookClient;
     
    @Autowired
    private MemberClient memberClient;
 
    private static final int MAX_BORROW_LIMIT = 5;
 
    @Override
    public BorrowingTransaction borrowBook(Long memberId, Long bookId) {
        Book book = bookclient.findById(bookId)
            .orElseThrow(() -> new BookNotFoundException("Book not found with ID: " + bookId));
 
        if (book.getAvailableCopies() <= 0) {
            throw new RuntimeException("Book is currently unavailable.");
        }
 
        Member member = memberclient.findById(memberId)
            .orElseThrow(() -> new MemberNotFoundException("Member not found with ID: " + memberId));
 
        long currentBorrowCount = transactionRepository.countByMemberIdAndStatus(memberId, "BORROWED");
        if (currentBorrowCount >= MAX_BORROW_LIMIT) {
            throw new RuntimeException("Borrowing limit reached.");
        }
 
        BorrowingTransaction transaction = new BorrowingTransaction();
        transaction.setBook(book);
        transaction.setMember(member);
        transaction.setBorrowDate(LocalDate.now());
        transaction.setStatus("BORROWED");
 
        book.setAvailableCopies(book.getAvailableCopies() - 1);
        bookclient.save(book);
        return transactionRepository.save(transaction);
    }
 
    @Override
    public BorrowingTransaction returnBook(Long transactionId) {
        BorrowingTransaction transaction = transactionRepository.findById(transactionId)
            .orElseThrow(() -> new TransactionNotFoundException("Transaction not found: " + transactionId));
 
        if (!"BORROWED".equals(transaction.getStatus())) {
            throw new RuntimeException("Book already returned or invalid status.");
        }
 
        transaction.setReturnDate(LocalDate.now());
        transaction.setStatus("RETURNED");
 
        Book book = transaction.getBook();
        book.setAvailableCopies(book.getAvailableCopies() + 1);
        bookclient.save(book);
 
        return transactionRepository.save(transaction);
    }
 
    @Override
    public BorrowingTransaction getTransactionById(Long transactionId) {
        return transactionRepository.findById(transactionId)
            .orElseThrow(() -> new TransactionNotFoundException("Transaction not found: " + transactionId));
    }
 
    @Override
    public List<BorrowingTransaction> getAllTransactions() {
        return transactionRepository.findAll();
    }
 
    @Override
    public List<BorrowingTransaction> getTransactionsByMemberId(Long memberId) {
        return transactionRepository.findByMemberId(memberId);
    }
 
    @Override
    public List<BorrowingTransaction> getBorrowedBooksByBookId(Long bookId) {
        return transactionRepository.findByBookIdAndStatus(bookId, "BORROWED");
    }
}
 
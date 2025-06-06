package com.cts.service;

import java.time.LocalDate;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.cts.dto.BorrowingTransactionDTO;
import com.cts.exception.BorrowingNotAllowedException;
import com.cts.exception.BorrowingTransactionNotFoundException;
import com.cts.feign.BookClient;
import com.cts.feign.MailClient;
import com.cts.feign.MemberClient;
import com.cts.model.BorrowingTransaction;
import com.cts.repository.BorrowingRepository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@AllArgsConstructor
public class BorrowingServiceImpl implements BorrowingService {

	private static final Logger logger = LoggerFactory.getLogger(BorrowingServiceImpl.class);

	private final BorrowingRepository repository;
	private final BookClient bookClient;
	private final MemberClient memberClient;
	private final MailClient mailClient;

	@Override
	public BorrowingTransaction borrowBook(BorrowingTransactionDTO transactionDTO) {
	logger.info("Attempting to borrow book with ID: {} for member ID: {}", transactionDTO.getBookId(), transactionDTO.getMemberId());
	 
	    // Validate member and book
	    memberClient.getMember(transactionDTO.getMemberId()); // Ensure member exists
	    bookClient.getBook(transactionDTO.getBookId());       // Ensure book exists
	 
	    // Check for borrowing limit
	    long activeBorrowings = repository.findByMemberIdAndStatus(transactionDTO.getMemberId(), BorrowingTransaction.Status.BORROWED).size();
	    if (activeBorrowings >= 5) {
	        logger.warn("Borrowing limit exceeded for member ID: {}", transactionDTO.getMemberId());
	        throw new BorrowingNotAllowedException("Borrowing limit exceeded");
	    }
	 
	    // Check if the same book is already borrowed by the same member
	    boolean alreadyBorrowedSameBook = repository
	        .findByBookIdAndMemberIdAndStatus(transactionDTO.getBookId(), transactionDTO.getMemberId(), BorrowingTransaction.Status.BORROWED)
	        .isPresent();
	 
	    if (alreadyBorrowedSameBook) {
	        logger.warn("Member ID: {} is already borrowing Book ID: {}", transactionDTO.getMemberId(), transactionDTO.getBookId());
	        throw new BorrowingNotAllowedException("Book is already borrowed by this member");
	    }
	 
	    // Create new borrowing transaction
	    BorrowingTransaction tx = BorrowingTransaction.builder()
	            .bookId(transactionDTO.getBookId())
	            .memberId(transactionDTO.getMemberId())
	            .borrowDate(LocalDate.now())
	            .status(BorrowingTransaction.Status.BORROWED)
	            .build();
	 
	    repository.save(tx);
	    log.info("New Booking is added");
		String msg="Successfully borrowed  :"+transactionDTO.getBookId()+" Book by Member "+transactionDTO.getMemberId()+" ";
		mailClient.sendEmail("ms12032004tsi@gmail.com","Hotel Booking System",msg);
	logger.info("Book borrowed successfully: Book ID={}, Member ID={}", transactionDTO.getBookId(), transactionDTO.getMemberId());
	    return tx;
	}
	
	@Override
	public List<BorrowingTransaction> getAllBorrows() {
	    log.debug("Fetching all Borrows");
	    return repository.findAll();
	}


	@Override
	public BorrowingTransaction returnBook(Long memberId, Long bookId) {
		logger.info("Attempting to return book with ID: {} for member ID: {}", bookId, memberId);

		BorrowingTransaction tx = repository
				.findByBookIdAndMemberIdAndStatus(bookId, memberId, BorrowingTransaction.Status.BORROWED)
				.orElseThrow(() -> {
					logger.error("No active borrowing found for book ID: {} and member ID: {}", bookId, memberId);
					return new BorrowingTransactionNotFoundException("No active borrowing found");
				});

		tx.setStatus(BorrowingTransaction.Status.RETURNED);
		tx.setReturnDate(LocalDate.now());
		repository.save(tx);

		logger.info("Book returned successfully: Book ID={}, Member ID={}", bookId, memberId);
		return tx;
	}

	@Override
	public List<BorrowingTransaction> getMemberBorrowedBooks(Long memberId) {
		logger.debug("Fetching borrowed books for member ID: {}", memberId);

		List<BorrowingTransaction> borrowedBooks = repository.findByMemberIdAndStatus(memberId,
				BorrowingTransaction.Status.BORROWED);

		logger.info("Retrieved {} borrowed books for member ID: {}", borrowedBooks.size(), memberId);
		return borrowedBooks;
	}

}
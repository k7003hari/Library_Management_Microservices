package com.cts.service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.cts.dto.BorrowingDTO;
import com.cts.exception.BookAlreadyReturnedException;
import com.cts.exception.BookNotFoundException;
import com.cts.exception.BookUnavailableException;
import com.cts.exception.BorrowingNotFoundException;
import com.cts.exception.MemberInactiveException;
import com.cts.exception.MemberNotFoundException;
import com.cts.feign.BookClient;
import com.cts.feign.MemberClient;
import com.cts.model.Borrowing;
import com.cts.repository.BorrowingRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class BorrowingServiceImpl implements BorrowingService {

	private final BorrowingRepository borrowingRepository;
	private final BookClient bookClient;
	private final MemberClient memberClient;

	@Override
	public BorrowingDTO borrowBook(BorrowingDTO dto) {
		log.info("Attempting to borrow book: {}", dto);

		if (!memberClient.isMemberExist(dto.getMemberId())) {
			throw new MemberNotFoundException("Member ID not found");
		}
		if (!memberClient.isMemberActive(dto.getMemberId())) {
			throw new MemberInactiveException("Member is inactive");
		}
		if (!bookClient.isBookExist(dto.getBookId())) {
			throw new BookNotFoundException("Book ID not found");
		}
		if (!bookClient.isBookAvailable(dto.getBookId())) {
			throw new BookUnavailableException("Book is not available for borrowing");
		}

		Borrowing borrowing = new Borrowing();
		borrowing.setMemberId(dto.getMemberId());
		borrowing.setBookId(dto.getBookId());
		borrowing.setBorrowDate(LocalDate.now());
		borrowing.setReturned(false);

		borrowing = borrowingRepository.save(borrowing);
		log.info("Book borrowed successfully with transactionId: {}", borrowing.getTransactionId());

		return toDTO(borrowing);
	}

	@Override
	public BorrowingDTO returnBook(Long transactionId) {
		log.info("Attempting to return book with transactionId: {}", transactionId);
		Borrowing borrowing = borrowingRepository.findById(transactionId)
				.orElseThrow(() -> new BorrowingNotFoundException("Transaction not found"));

		if (borrowing.isReturned()) {
			throw new BookAlreadyReturnedException("Book has already been returned");
		}

		borrowing.setReturned(true);
		borrowing.setReturnDate(LocalDate.now());
		borrowing = borrowingRepository.save(borrowing);

		log.info("Book returned for transactionId: {}", transactionId);
		return toDTO(borrowing);
	}

	@Override
	public List<BorrowingDTO> getBorrowingsByMember(Long memberId) {
		log.info("Fetching borrowings for memberId: {}", memberId);
		return borrowingRepository.findByMemberId(memberId).stream().map(this::toDTO).collect(Collectors.toList());
	}

	@Override
	public boolean isBookBorrowed(Long bookId) {
		log.info("Checking if bookId {} is currently borrowed", bookId);
		return !borrowingRepository.findByBookIdAndReturnedFalse(bookId).isEmpty();
	}

	@Override
	public BorrowingDTO getBorrowingByTransactionId(Long transactionId) {
		log.info("Fetching borrowing by transactionId: {}", transactionId);
		return borrowingRepository.findById(transactionId).map(this::toDTO)
				.orElseThrow(() -> new BorrowingNotFoundException("Transaction ID not found"));
	}

	@Override
	public List<BorrowingDTO> getAllBorrowings() {
		log.info("Fetching all borrowings");
		return borrowingRepository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
	}

	private BorrowingDTO toDTO(Borrowing b) {
		return new BorrowingDTO(b.getTransactionId(), b.getMemberId(), b.getBookId(), b.getBorrowDate(),
				b.getReturnDate(), b.isReturned());
	}
}
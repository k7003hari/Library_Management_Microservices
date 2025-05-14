package com.cts.service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.cts.dto.BorrowingTransactionDTO;
import com.cts.exception.BorrowingNotAllowedException;
import com.cts.exception.BorrowingTransactionNotFoundException;
import com.cts.feign.BookClient;
import com.cts.feign.MemberClient;
import com.cts.model.BorrowingTransaction;
import com.cts.repository.BorrowingRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class BorrowingServiceImpl implements BorrowingService {

	private final BorrowingRepository repository;
	private final BookClient bookClient;
	private final MemberClient memberClient;

	@Override
	public BorrowingTransactionDTO borrowBook(Long memberId, Long bookId) {
		memberClient.getMember(memberId); // Ensure member exists
		bookClient.getBook(bookId); // Ensure book exists

		long activeBorrowings = repository.findByMemberIdAndStatus(memberId, BorrowingTransaction.Status.BORROWED)
				.size();
		if (activeBorrowings >= 5) {
			throw new BorrowingNotAllowedException("Borrowing limit exceeded");
		}

		BorrowingTransaction tx = BorrowingTransaction.builder().bookId(bookId).memberId(memberId)
				.borrowDate(LocalDate.now()).status(BorrowingTransaction.Status.BORROWED).build();

		repository.save(tx);
		return mapToDTO(tx);
	}

	@Override
	public BorrowingTransactionDTO returnBook(Long memberId, Long bookId) {
		BorrowingTransaction tx = repository
				.findByBookIdAndMemberIdAndStatus(bookId, memberId, BorrowingTransaction.Status.BORROWED)
				.orElseThrow(() -> new BorrowingTransactionNotFoundException("No active borrowing found"));

		tx.setStatus(BorrowingTransaction.Status.RETURNED);
		tx.setReturnDate(LocalDate.now());
		repository.save(tx);

		return mapToDTO(tx);
	}

	@Override
	public List<BorrowingTransactionDTO> getMemberBorrowedBooks(Long memberId) {
		return repository.findByMemberIdAndStatus(memberId, BorrowingTransaction.Status.BORROWED).stream()
				.map(this::mapToDTO).collect(Collectors.toList());
	}

	private BorrowingTransactionDTO mapToDTO(BorrowingTransaction tx) {
		return BorrowingTransactionDTO.builder().transactionId(tx.getTransactionId()).bookId(tx.getBookId())
				.memberId(tx.getMemberId()).borrowDate(tx.getBorrowDate()).returnDate(tx.getReturnDate())
				.status(tx.getStatus().name()).build();
	}
}
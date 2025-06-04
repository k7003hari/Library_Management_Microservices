package com.cts.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cts.model.BorrowingTransaction;

public interface BorrowingRepository extends JpaRepository<BorrowingTransaction, Long> {

	List<BorrowingTransaction> findByMemberIdAndStatus(Long memberId, BorrowingTransaction.Status status);

	Optional<BorrowingTransaction> findByBookIdAndMemberIdAndStatus(Long bookId, Long memberId,
			BorrowingTransaction.Status status);
	
//	List<BorrowingTransaction> findAll();

}
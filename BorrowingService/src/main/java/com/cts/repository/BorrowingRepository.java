package com.cts.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cts.model.Borrowing;

public interface BorrowingRepository extends JpaRepository<Borrowing, Long> {

	List<Borrowing> findByMemberId(Long memberId);

	List<Borrowing> findByBookIdAndReturnedFalse(Long bookId);
	
	boolean existsByBookIdAndReturnedFalse(Long bookId);
}
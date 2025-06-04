package com.cts.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cts.model.Fine;
import com.cts.model.Fine.FineStatus;

public interface FineRepository extends JpaRepository<Fine, Long> {
	List<Fine> findByMemberId(Long memberId);

	List<Fine> findByMemberIdAndStatus(Long memberId, Fine.FineStatus status);
}
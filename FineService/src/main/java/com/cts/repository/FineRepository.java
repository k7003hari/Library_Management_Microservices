package com.cts.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cts.model.Fine;

public interface FineRepository extends JpaRepository<Fine, Long> {
	List<Fine> findByMemberId(Long memberId);
}
package com.cts.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cts.model.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {
	List<Member> findByNameContainingIgnoreCase(String name);

	boolean existsByEmail(String email);
}
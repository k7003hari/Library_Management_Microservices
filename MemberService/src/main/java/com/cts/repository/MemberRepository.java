package com.cts.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cts.model.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {
}

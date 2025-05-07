package com.cts.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.cts.model.Fine;

import java.util.List;
 
public interface FineRepository extends JpaRepository<Fine, Long> {
    List<Fine> findByMemberId(Long memberId);
}

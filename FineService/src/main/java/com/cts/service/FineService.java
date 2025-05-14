package com.cts.service;

import java.util.List;

import com.cts.dto.FineDTO;

public interface FineService {

	FineDTO calculateFine(Long memberId);

	void payFine(Long memberId, Long fineId);

	List<FineDTO> getAllFines();
}
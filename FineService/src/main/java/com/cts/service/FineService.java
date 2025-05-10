package com.cts.service;

import java.util.List;

import com.cts.dto.FineDTO;

public interface FineService {
	 
    FineDTO issueFine(FineDTO fineDTO);
 
    FineDTO payFine(Long fineId);
 
    FineDTO getFineById(Long fineId);
 
    List<FineDTO> getAllFines();
 
    List<FineDTO> getFinesByMemberId(Long memberId);
 
    boolean isFineExist(Long fineId);
 
    boolean isFinePaid(Long fineId);
}
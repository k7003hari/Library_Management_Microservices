package com.cts.service;


import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.ctc.exception.FineAlreadyPaidException;
import com.ctc.exception.FineNotFoundException;
import com.cts.dto.FineDTO;
import com.cts.feign.BookClient;
import com.cts.feign.MemberClient;
import com.cts.model.Fine;
import com.cts.repository.FineRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
 
@Service
@RequiredArgsConstructor
@Slf4j
public class FineServiceImpl implements FineService {
 
    private final FineRepository fineRepository;
    private final MemberClient memberClient;
    private final BookClient bookClient;
 
    // Add a fine
    @Override
    public FineDTO issueFine(FineDTO fineDTO) {
        log.info("Issuing fine for member ID {} and book ID {}", fineDTO.getMemberId(), fineDTO.getBookId());
 
        if (!memberClient.isMemberExist(fineDTO.getMemberId())) {
            throw new FineNotFoundException("Member not found with ID: " + fineDTO.getMemberId());
        }
 
        if (!bookClient.isBookExist(fineDTO.getBookId())) {
            throw new FineNotFoundException("Book not found with ID: " + fineDTO.getBookId());
        }
 
        Fine fine = new Fine();
        fine.setMemberId(fineDTO.getMemberId());
        fine.setBookId(fineDTO.getBookId());
        fine.setAmount(fineDTO.getAmount());
        fine.setIssuedDate(LocalDate.now());
        fine.setPaid(false);
 
        fine = fineRepository.save(fine);
        fineDTO.setFineId(fine.getFineId());
        fineDTO.setIssuedDate(fine.getIssuedDate());
        fineDTO.setPaid(false);
 
        log.info("Fine issued with ID {}", fine.getFineId());
        return fineDTO;
    }
 
    // Pay a fine
    @Override
    public FineDTO payFine(Long fineId) {
        log.info("Processing fine payment for ID {}", fineId);
        Fine fine = fineRepository.findById(fineId)
                .orElseThrow(() -> new FineNotFoundException("Fine not found with ID: " + fineId));
 
        if (fine.isPaid()) {
            throw new FineAlreadyPaidException("Fine already paid for ID: " + fineId);
        }
 
        fine.setPaid(true);
        fineRepository.save(fine);
 
        return mapToDTO(fine);
    }
 
    // Get fine by ID
    @Override
    public FineDTO getFineById(Long fineId) {
        log.info("Fetching fine with ID {}", fineId);
        Fine fine = fineRepository.findById(fineId)
                .orElseThrow(() -> new FineNotFoundException("Fine not found with ID: " + fineId));
        return mapToDTO(fine);
    }
 
    // Get all fines
    @Override
    public List<FineDTO> getAllFines() {
        log.info("Fetching all fines");
        return fineRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }
 
    // Get fines by member
    @Override
    public List<FineDTO> getFinesByMemberId(Long memberId) {
        log.info("Fetching fines for member ID {}", memberId);
        return fineRepository.findByMemberId(memberId).stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }
 
    @Override
    public boolean isFineExist(Long fineId) {
        return fineRepository.existsByFineId(fineId);
    }
 
    @Override
    public boolean isFinePaid(Long fineId) {
        return fineRepository.existsByFineIdAndPaidTrue(fineId);
    }
 
    private FineDTO mapToDTO(Fine fine) {
        FineDTO dto = new FineDTO();
        dto.setFineId(fine.getFineId());
        dto.setMemberId(fine.getMemberId());
        dto.setBookId(fine.getBookId());
        dto.setAmount(fine.getAmount());
        dto.setIssuedDate(fine.getIssuedDate());
        dto.setPaid(fine.isPaid());
        return dto;
    }
}
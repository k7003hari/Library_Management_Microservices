package com.cts.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cts.dto.FineDTO;
import com.cts.service.FineService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/fines")
@RequiredArgsConstructor
@Slf4j
public class FineController {

	private final FineService fineService;

	// Issue a fine
	@PostMapping("/issue")
	public FineDTO issueFine(@Valid @RequestBody FineDTO fineDTO) {
		log.info("Request to issue fine");
		return fineService.issueFine(fineDTO);
	}

	// Pay a fine
	@PutMapping("/pay/{fineId}")
	public FineDTO payFine(@PathVariable Long fineId) {
		log.info("Request to pay fine with ID: {}", fineId);
		return fineService.payFine(fineId);
	}

	// Get fine by ID
	@GetMapping("/{fineId}")
	public FineDTO getFineById(@PathVariable Long fineId) {
		log.info("Request to get fine with ID: {}", fineId);
		return fineService.getFineById(fineId);
	}

	// Get all fines
	@GetMapping("/all")
	public List<FineDTO> getAllFines() {
		log.info("Request to get all fines");
		return fineService.getAllFines();
	}

	// Get all fines by Member ID
	@GetMapping("/member/{memberId}")
	public List<FineDTO> getFinesByMemberId(@PathVariable Long memberId) {
		log.info("Request to get fines for member ID: {}", memberId);
		return fineService.getFinesByMemberId(memberId);
	}

	// Check if fine exists
	@GetMapping("/exists/{fineId}")
	public boolean isFineExist(@PathVariable Long fineId) {
		log.info("Checking if fine exists with ID: {}", fineId);
		return fineService.isFineExist(fineId);
	}

	// Check if fine is paid
	@GetMapping("/paid/{fineId}")
	public boolean isFinePaid(@PathVariable Long fineId) {
		log.info("Checking if fine is paid for ID: {}", fineId);
		return fineService.isFinePaid(fineId);
	}
}
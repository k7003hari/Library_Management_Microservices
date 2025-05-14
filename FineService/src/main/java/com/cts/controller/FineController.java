package com.cts.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cts.dto.FineDTO;
import com.cts.service.FineService;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/fines")
@AllArgsConstructor
public class FineController {
 
    private final FineService fineService;
 
    // Endpoint to calculate fine for a member
    @PostMapping("/calculatefine/{memberId}")
    public FineDTO calculateFine(@PathVariable Long memberId) {
        return fineService.calculateFine(memberId);
    }
 
    // Endpoint for member to pay their fine
    @PostMapping("/pay/{memberId}/{fineId}")
    public void payFine(@PathVariable Long memberId, @PathVariable Long fineId) {
        fineService.payFine(memberId, fineId);
    }
 
    // Endpoint for Admin to view all fines
    @GetMapping("/allfines")
    public List<FineDTO> getAllFines() {
        return fineService.getAllFines();
    }
}
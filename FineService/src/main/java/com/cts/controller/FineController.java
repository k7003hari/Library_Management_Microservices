package com.cts.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cts.model.Fine;
import com.cts.service.FineService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/fines")
@RequiredArgsConstructor
public class FineController {
 
    private final FineService fineService;
 
    @PostMapping("/calculate/{transactionId}")
    public Fine calculateFine(@PathVariable Long transactionId) {
        return fineService.calculateFine(transactionId);
    }
 
    @PutMapping("/pay/{fineId}")
    public Fine payFine(@PathVariable Long fineId) {
        return fineService.payFine(fineId);
    }
 
    @GetMapping("/member/{memberId}")
    public List<Fine> getFinesByMember(@PathVariable Long memberId) {
        return fineService.getFinesByMemberId(memberId);
    }
}
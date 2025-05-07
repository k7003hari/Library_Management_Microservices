package com.cts.service;

import java.util.List;

import com.cts.model.Fine;

public interface FineService {
    Fine calculateFine(Long transactionId);
    Fine payFine(Long fineId);
    List<Fine> getFinesByMemberId(Long memberId);
}

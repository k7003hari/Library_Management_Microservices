package com.cts.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.cts.dto.BorrowingTransactionDTO;

@FeignClient(name = "BORROWINGSERVICE", path = "/borrowings")
public interface BorrowingClient {

    @GetMapping("/{memberId}")
    List<BorrowingTransactionDTO> getBorrowedBooksByMember(@PathVariable("memberId") Long memberId);
}


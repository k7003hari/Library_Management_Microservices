package com.cts.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.cts.dto.BorrowingTransactionDTO;
 
@FeignClient(name = "BORROWING-SERVICE" ,path = "/fines")
public interface BorrowingClient {
 
    @GetMapping("/borrowing/{transactionId}")
    BorrowingTransactionDTO getTransactionById(@PathVariable Long transactionId);
}
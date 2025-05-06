package com.cts.feignClient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
 
@FeignClient(name = "book-service", url = "http://localhost:8081/api/books")
public interface BookServiceClient {
 
    @GetMapping("/{bookId}/availability")
    boolean isBookAvailable(@PathVariable("bookId") Long bookId);
 
    @GetMapping("/{bookId}/exists")
    boolean bookExists(@PathVariable("bookId") Long bookId);
}
package com.cts.feignClient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@FeignClient(name = "book-service", url = "http://localhost:8081/api/books")
public interface BookClient {
 
    @GetMapping("/{bookId}")
    BookDTO getBookById(@PathVariable Long bookId);
 
    @PutMapping("/{bookId}/decrease")
    void decreaseAvailableCopies(@PathVariable Long bookId);
 
    @PutMapping("/{bookId}/increase")
    void increaseAvailableCopies(@PathVariable Long bookId);
}

package com.cts.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cts.dto.BookDTO;

import lombok.Data;
import lombok.Getter;

@FeignClient(name = "BOOKSERVICE", path="/books")
public interface BookClient {
    @GetMapping("/getById/{id}")
    BookDTO getBook(@PathVariable Long id);
 
    @PutMapping("/books/{id}/copies")
    void updateBookCopies(@PathVariable Long id, @RequestParam int availableCopies);


	
}
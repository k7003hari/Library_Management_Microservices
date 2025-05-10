package com.cts.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "book-service")
public interface BookClient {
	@GetMapping("/books/exists/{id}")
	boolean isBookExist(@PathVariable Long id);

	@GetMapping("/books/available/{id}")
	boolean isBookAvailable(@PathVariable Long id);
}
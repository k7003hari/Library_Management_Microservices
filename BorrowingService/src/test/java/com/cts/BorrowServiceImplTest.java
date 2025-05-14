package com.cts;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.cts.service.BorrowingService;

@SpringBootApplication
class BorrowingServiceImplTest {
	@Autowired
	private BorrowingService service;

	@Test
	void testBorrowBookLimitExceeded() {
		Long memberId = 1L;
		Long bookId = 100L;

		Assertions.assertThrows(Exception.class, () -> {
			service.borrowBook(memberId, bookId);
		});
	}
}
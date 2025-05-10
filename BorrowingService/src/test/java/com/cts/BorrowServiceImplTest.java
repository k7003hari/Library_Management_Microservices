package com.cts;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.cts.dto.BorrowingDTO;
import com.cts.exception.BorrowingNotFoundException;
import com.cts.feign.BookClient;
import com.cts.feign.MemberClient;
import com.cts.model.Borrowing;
import com.cts.repository.BorrowingRepository;
import com.cts.service.BorrowingServiceImpl;

class BorrowingServiceImplTest {

	@Mock
	private BorrowingRepository borrowingRepository;

	@Mock
	private BookClient bookClient;

	@Mock
	private MemberClient memberClient;

	@InjectMocks
	private BorrowingServiceImpl borrowingService;

	private AutoCloseable closeable;

	private Borrowing borrowing;

	@BeforeEach
	void setUp() {
		closeable = MockitoAnnotations.openMocks(this);
		borrowing = new Borrowing(1L, 101L, 202L, LocalDate.now(), null, false);
	}

	@AfterEach
	void tearDown() throws Exception {
		closeable.close();
	}

	@Test
	void testBorrowBook_Success() {
		when(memberClient.isMemberExist(101L)).thenReturn(true);
		when(memberClient.isMemberActive(101L)).thenReturn(true);
		when(bookClient.isBookExist(202L)).thenReturn(true);
		when(bookClient.isBookAvailable(202L)).thenReturn(true);
		when(borrowingRepository.save(any())).thenReturn(borrowing);

		BorrowingDTO dto = new BorrowingDTO(null, 101L, 202L, null, null, false);
		BorrowingDTO result = borrowingService.borrowBook(dto);

		assertNotNull(result);
		assertEquals(101L, result.getMemberId());
		verify(borrowingRepository, times(1)).save(any());
	}

	@Test
	void testReturnBook_NotFound() {
		when(borrowingRepository.findById(1L)).thenReturn(Optional.empty());

		assertThrows(BorrowingNotFoundException.class, () -> {
			borrowingService.returnBook(1L);
		});
	}

	@Test
	void testIsBookBorrowed() {
		when(borrowingRepository.existsByBookIdAndReturnedFalse(202L)).thenReturn(true);
		assertTrue(borrowingService.isBookBorrowed(202L));
	}

	@Test
	void testGetBorrowingsByMember() {
		when(borrowingRepository.findByMemberId(101L)).thenReturn(List.of(borrowing));
		List<BorrowingDTO> result = borrowingService.getBorrowingsByMember(101L);

		assertEquals(1, result.size());
		assertEquals(101L, result.get(0).getMemberId());
	}
}

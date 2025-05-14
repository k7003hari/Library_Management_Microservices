package com.cts.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.ctc.exception.FineNotFoundException;
import com.ctc.exception.UnauthorizedAccessException;
import com.cts.dto.BorrowingTransactionDTO;
import com.cts.dto.FineDTO;
import com.cts.feign.BorrowingClient;
import com.cts.feign.MemberClient;
import com.cts.model.Fine;
import com.cts.repository.FineRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class FineServiceImpl implements FineService {

	private final FineRepository fineRepository;
	private final BorrowingClient borrowingClient;
	private final MemberClient memberClient;

	// Method to calculate fines
	@Override
	public FineDTO calculateFine(Long memberId) {
		List<BorrowingTransactionDTO> borrowedBooks = borrowingClient.getBorrowedBooksByMember(memberId);

		BigDecimal totalFine = BigDecimal.ZERO;
		for (BorrowingTransactionDTO transaction : borrowedBooks) {
			long overdueDays = calculateOverdueDays(transaction.getReturnDate());
			if (overdueDays > 0) {
				totalFine = totalFine.add(BigDecimal.valueOf(overdueDays).multiply(new BigDecimal("1.00"))); // 1 unit
																												// of
																												// currency
																												// per
																												// overdue
																												// day
			}
		}

		Fine fine = Fine.builder().member(memberClient.getMemberById(memberId)) // Assuming you have a service to get
																				// member details
				.amount(totalFine).status(Fine.FineStatus.PENDING).transactionDate(LocalDate.now()).build();

		fineRepository.save(fine);
		log.info("Fine calculated for Member ID: {}", memberId);
		return mapToDTO(fine);
	}

	// Method to calculate overdue days
	private long calculateOverdueDays(LocalDate returnDate) {
		if (returnDate == null || returnDate.isBefore(LocalDate.now())) {
			return returnDate == null ? 0 : returnDate.until(LocalDate.now()).getDays();
		}
		return 0;
	}

	// Method to pay fine (only the member who owes can pay)
	@Override
	public void payFine(Long memberId, Long fineId) {
		Fine fine = fineRepository.findById(fineId)
				.orElseThrow(() -> new FineNotFoundException("Fine not found with ID: " + fineId));

		if (!fine.getMember().getMemberId().equals(memberId)) {
			throw new UnauthorizedAccessException("You are not authorized to pay this fine.");
		}

		fine.setStatus(Fine.FineStatus.PAID);
		fineRepository.save(fine);
		log.info("Fine paid for Member ID: {}", memberId);
	}

	// Method for Admin to view all fines
	@Override
	public List<FineDTO> getAllFines() {
		List<Fine> fines = fineRepository.findAll();
		return fines.stream().map(this::mapToDTO).collect(Collectors.toList());
	}

	private FineDTO mapToDTO(Fine fine) {
		return FineDTO.builder().fineId(fine.getFineId()).memberId(fine.getMember().getMemberId())
				.amount(fine.getAmount()).status(fine.getStatus().name()) // Convert enum to String
				.transactionDate(fine.getTransactionDate()).build();
	}
}
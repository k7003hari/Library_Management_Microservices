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

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Slf4j
public class FineServiceImpl implements FineService {

    private final FineRepository fineRepository;
    private final BorrowingClient borrowingClient;
    private final MemberClient memberClient;

    @Override
    public FineDTO calculateFine(Long memberId) {
        log.info("Calculating fine for member ID: {}", memberId);
        List<BorrowingTransactionDTO> borrowedBooks = borrowingClient.getBorrowedBooksByMember(memberId);

        BigDecimal totalFine = BigDecimal.ZERO;
        for (BorrowingTransactionDTO transaction : borrowedBooks) {
            long overdueDays = calculateOverdueDays(transaction.getReturnDate());
            if (overdueDays > 0) {
                totalFine = totalFine.add(BigDecimal.valueOf(overdueDays).multiply(new BigDecimal("1.00")));
            }
        }

        Fine fine = Fine.builder()
                .memberId(memberId)
                .amount(totalFine)
                .status(Fine.FineStatus.PENDING)
                .transactionDate(LocalDate.now())
                .build();

        fineRepository.save(fine);
        log.info("Fine calculated for member ID: {}", memberId);
        return new FineDTO(fine.getFineId(), memberClient.getMemberById(memberId), fine.getAmount(), fine.getStatus().name(), fine.getTransactionDate());
    }

    private long calculateOverdueDays(LocalDate returnDate) {
        if (returnDate == null || returnDate.isBefore(LocalDate.now())) {
            return returnDate == null ? 0 : returnDate.until(LocalDate.now()).getDays();
        }
        return 0;
    }

    @Override
    public void payFine(Long memberId, Long fineId) {
        log.info("Paying fine with ID: {} for member ID: {}", fineId, memberId);
        Fine fine = fineRepository.findById(fineId)
                .orElseThrow(() -> new FineNotFoundException("Fine not found with ID: " + fineId));

        if (!fine.getMemberId().equals(memberId)) {
            log.error("Unauthorized access attempt by member ID: {}", memberId);
            throw new UnauthorizedAccessException("You are not authorized to pay this fine.");
        }

        fine.setStatus(Fine.FineStatus.PAID);
        fineRepository.save(fine);
        log.info("Fine paid for member ID: {}", memberId);
    }

    @Override
    public List<FineDTO> getAllFines() {
        log.debug("Fetching all fines");
        List<Fine> fines = fineRepository.findAll();
        return fines.stream()
                .map(fine -> new FineDTO(fine.getFineId(), memberClient.getMemberById(fine.getMemberId()), fine.getAmount(), fine.getStatus().name(), fine.getTransactionDate()))
                .collect(Collectors.toList());
    }
}

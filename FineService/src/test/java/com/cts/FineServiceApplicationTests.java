package com.cts;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.ctc.exception.FineAlreadyPaidException;
import com.ctc.exception.FineNotFoundException;
import com.cts.dto.FineDTO;
import com.cts.model.Fine;
import com.cts.repository.FineRepository;
import com.cts.service.FineServiceImpl;

@SpringBootTest
class FineServiceImplTest {
	 
    @InjectMocks
    private FineServiceImpl fineService;
 
    @Mock
    private FineRepository fineRepository;
 
    private AutoCloseable closeable;
    private Fine fine;
 
    @BeforeEach
    void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
        fine = new Fine(1L, 101L, 201L, 50.0, false, LocalDate.now());
    }
 
    @AfterEach
    void tearDown() throws Exception {
        closeable.close();
    }
 
    @Test
    void testIssueFine() {
        when(fineRepository.save(any(Fine.class))).thenReturn(fine);
        FineDTO result = fineService.issueFine(new FineDTO(null, 101L, 201L, 50.0, false, LocalDate.now()));
        assertNotNull(result);
        assertEquals(101L, result.getMemberId());
    }
 
    @Test
    void testPayFineSuccess() {
        fine.setPaid(false);
        when(fineRepository.findById(1L)).thenReturn(Optional.of(fine));
        when(fineRepository.save(any(Fine.class))).thenReturn(fine);
 
        FineDTO result = fineService.payFine(1L);
        assertTrue(result.isPaid());
    }
 
    @Test
    void testPayFineAlreadyPaid() {
        fine.setPaid(true);
        when(fineRepository.findById(1L)).thenReturn(Optional.of(fine));
        assertThrows(FineAlreadyPaidException.class, () -> fineService.payFine(1L));
    }
 
    @Test
    void testGetFineByIdNotFound() {
        when(fineRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(FineNotFoundException.class, () -> fineService.getFineById(1L));
    }
 
    @Test
    void testIsFineExistTrue() {
        when(fineRepository.existsById(1L)).thenReturn(true);
        assertTrue(fineService.isFineExist(1L));
    }
 
    @Test
    void testIsFinePaid() {
        fine.setPaid(true);
        when(fineRepository.findById(1L)).thenReturn(Optional.of(fine));
        assertTrue(fineService.isFinePaid(1L));
    }
}

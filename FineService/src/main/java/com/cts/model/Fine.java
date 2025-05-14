package com.cts.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.boot.json.JsonWriter.Member;

import com.cts.dto.MemberDTO;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Fine {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long fineId;

	@ManyToOne
	@JoinColumn(name = "member_id", nullable = false)
	private MemberDTO member;

	private BigDecimal amount;

	@Enumerated(EnumType.STRING)
	private FineStatus status;

	private LocalDate transactionDate;

	public enum FineStatus {
		PAID, PENDING
	}
}
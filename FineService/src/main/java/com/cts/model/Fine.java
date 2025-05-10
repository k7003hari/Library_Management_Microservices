package com.cts.model;


import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "fines")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Fine {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long fineId;

	private Long memberId;
	private Long bookId;
	private Double amount;
	private LocalDate issuedDate;
	private boolean paid;
}

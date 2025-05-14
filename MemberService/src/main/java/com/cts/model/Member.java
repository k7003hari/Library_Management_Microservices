package com.cts.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "member")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Member {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long memberId;

	@NotBlank(message = "Name must not be blank")
	@Size(min = 2, message = "Name must be at least 2 characters")
	private String name;

	@Email(message = "Email should be valid")
	@NotBlank(message = "Email must not be blank")
	private String email;

	@Pattern(regexp = "\\d{10}", message = "Phone number must be 10 digits")
	private String phone;

	@NotBlank(message = "Address must not be blank")
	private String address;

	@NotBlank(message = "Membership status must be provided")
	private String membershipStatus;
}
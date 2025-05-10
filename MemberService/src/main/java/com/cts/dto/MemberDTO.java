package com.cts.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberDTO {

	private Long id;

	@NotBlank(message = "Name is mandatory")
	private String name;

	@Email(message = "Invalid email format")
	@NotBlank(message = "Email is required")
	private String email;

	@NotBlank(message = "Phone is required")
	private String phone;

	@NotBlank(message = "Membership type is required")
	private String membershipType;

	@NotNull(message = "Active status must be specified")
	private Boolean active;
}
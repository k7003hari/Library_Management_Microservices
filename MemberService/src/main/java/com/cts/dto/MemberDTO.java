package com.cts.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberDTO {
	 
    private Long memberId;
    private String name;
    private String email;
    private String phone;
    private String address;
    private String membershipStatus;
}
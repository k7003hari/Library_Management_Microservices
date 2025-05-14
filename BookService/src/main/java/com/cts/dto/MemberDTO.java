package com.cts.dto;

import lombok.Data;

@Data
public class MemberDTO {
    private Long memberId;
    private String name;
    private String email;
    private String phone;
    private String address;
    private String membershipStatus;
}
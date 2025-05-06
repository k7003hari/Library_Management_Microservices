package com.cts.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberDTO {
    private String name;
    private String email;
    private String phone;
    private String address;
    private String membershipStatus;
}

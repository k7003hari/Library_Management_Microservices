package com.cts.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
 
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NotificationDTO {
 
    private Long notificationId;
 
    @NotNull(message = "Member ID cannot be null")
    private Long memberId;
 
    @NotBlank(message = "Message cannot be blank")
    private String message;
 
    private LocalDate dateSent;
}
 
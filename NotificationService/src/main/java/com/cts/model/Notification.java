package com.cts.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Notification {
 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long notificationId;
 
    private Long memberId;
 
    @Column(nullable = false)
    private String message;
 
    private LocalDate dateSent;
}
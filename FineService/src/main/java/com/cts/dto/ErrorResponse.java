package com.cts.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ErrorResponse {
   private String code;
   private String message;
   private String path;
   private LocalDateTime timestamp = LocalDateTime.now();

   public ErrorResponse(String code, String message, String path) {
       this.code = code;
       this.message = message;
       this.path = path;
   }
}
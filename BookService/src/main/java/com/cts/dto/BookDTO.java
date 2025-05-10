package com.cts.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookDTO {
 
    private Long bookId;
 
    @NotBlank(message = "Title cannot be blank")
    private String title;
 
    @NotBlank(message = "Author cannot be blank")
    private String author;
 
    @NotBlank(message = "Genre cannot be blank")
    private String genre;
 
    @NotBlank(message = "ISBN cannot be blank")
    private String isbn;
 
    @Min(value = 1000, message = "Year published must be a valid year")
    private int yearPublished;
 
    @Min(value = 1, message = "Total copies must be at least 1")
    private int totalCopies;
 
    @Min(value = 0, message = "Available copies cannot be negative")
    private int availableCopies;
}
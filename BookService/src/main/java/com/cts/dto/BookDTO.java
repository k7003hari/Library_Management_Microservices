package com.cts.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookDTO {
    private String title;
    private String author;
    private String genre;
    private String isbn;
    private Integer yearPublished;
    private Integer availableCopies;
}
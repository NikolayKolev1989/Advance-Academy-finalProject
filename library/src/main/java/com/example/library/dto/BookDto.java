package com.example.library.dto;

import com.example.library.model.Author;
import com.example.library.model.Genre;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookDto {
    private Long id;

    private String title;

    private Genre genre;

    private Author author;

    private String clientNames;
}

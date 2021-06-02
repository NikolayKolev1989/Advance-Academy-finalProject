package com.example.library.converter;

import com.example.library.dto.BookDto;
import com.example.library.model.Book;
import com.example.library.model.Genre;
import org.springframework.stereotype.Component;

@Component
public class BookConverter {

    public BookDto map (Book book){
        return BookDto.builder()
                .id(book.getId())
                .title(book.getTitle())
                .author(book.getAuthor())
                .genre(book.getGenre())
                .clientNames(String.format(book.getClient().getFirstName()+
                        " " + book.getClient().getFirstName()))
                .build();
    }
    public Book map (BookDto bookDto){
        return Book.builder()
                .id(bookDto.getId())
                .title(bookDto.getTitle())
                .author(bookDto.getAuthor())
                .genre(bookDto.getGenre())
                .build();
    }
}

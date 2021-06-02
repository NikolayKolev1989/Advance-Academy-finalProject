package com.example.library.controller;

import com.example.library.converter.BookConverter;
import com.example.library.dto.BookDto;
import com.example.library.model.Book;
import com.example.library.service.BookService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;

@RestController
public class BookController {

    private BookService bookService;
    private final BookConverter bookConverter;

    @Autowired
    public BookController(BookService bookService, BookConverter bookConverter) {
        this.bookService = bookService;
        this.bookConverter = bookConverter;
    }

    @GetMapping(value = "/{book}")
    public ResponseEntity<BookDto> findByTitle(@PathVariable String title){
        Book book = bookService.findByTitle(title);
        return ResponseEntity.ok(bookConverter.map(book));
    }

    @GetMapping
    public ResponseEntity<Set<BookDto>> findAll(){
//        Set<BookDto> books = bookService.findAllBooks();
//        return ResponseEntity.ok(books);
        return ResponseEntity.ok(new HashSet<>(bookService.findAllBooks()));
    }

    @PostMapping
    public ResponseEntity<BookDto> safeBook (@RequestBody BookDto bookDto){
       Book book = bookService.safeBook(bookConverter.map(bookDto));
        return ResponseEntity.ok(bookConverter.map(book));
    }
    @DeleteMapping(value = "/{book}")
    public ResponseEntity<HttpStatus> removeBook(String title){
        bookService.removeBook(title);
        return ResponseEntity.ok().build();
    }
}

package com.example.library.controller;

import com.example.library.model.Author;
import com.example.library.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.util.Set;

@RestController
public class AuthorController {

    private AuthorService authorService;

    @Autowired
    public AuthorController(AuthorService authorService){
        this.authorService = authorService;
    }

    @GetMapping
    public ResponseEntity<Set<Author>> findAllAuthors(){
        return ResponseEntity.ok(authorService.findAllAuthors());
    }

    @PostMapping
    public ResponseEntity<HttpStatus> safeAuthor(@NotNull Author author){
        authorService.safeAuthor(author);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    public ResponseEntity<HttpStatus> deleteAuthorById(@NotNull Long id){
        authorService.deleteAuthorById(id);
        return ResponseEntity.ok().build();
    }
}

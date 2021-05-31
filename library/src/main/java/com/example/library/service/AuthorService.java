package com.example.library.service;

import com.example.library.model.Author;
import com.example.library.repository.AuthorRepository;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;

@Service
public class AuthorService {

    private final AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public Author safeAuthor(Author author) {
        return authorRepository.save(author);
    }

    public Set<Author> findAllAuthors() {
        return new HashSet<>(authorRepository.findAll());
    }

    public Author findByAuthorName(@NotNull String name) {
        return authorRepository.findByName(name)
                .orElseThrow(() -> new NoSuchElementException(
                        String.format("Author %s does not exist", name)));
    }

    public void deleteAuthorById(Long id) {
        authorRepository.deleteById(id);
    }
}

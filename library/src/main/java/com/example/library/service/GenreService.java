package com.example.library.service;

import com.example.library.model.Book;
import com.example.library.model.Genre;
import com.example.library.repository.GenreRepository;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;

@Service
public class GenreService {

    private GenreRepository genreRepository;

    public GenreService(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    public Genre safeBook(Genre genre) {
        return genreRepository.save(genre);
    }

    public Set<Genre> findAllGenres() {
        return new HashSet<>(genreRepository.findAll());
    }

    public Genre findByBookTitle(@NotNull String type) {
        return genreRepository.findByType(type)
                .orElseThrow(() -> new NoSuchElementException
                        (String.format("Genre %s does not exist", type)));
    }

    public void deleteGenreById(Long id) {
        genreRepository.deleteById(id);
    }
}

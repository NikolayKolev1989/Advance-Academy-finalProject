package com.example.library.service;

import com.example.library.dto.BookDto;
import com.example.library.model.Book;
import com.example.library.repository.BookRepository;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public BookDto findByTitle(@NotNull String title){
        Book bookByTitle = bookRepository.findByTitle(title)
                .orElseThrow(() -> new NoSuchElementException());

        BookDto bookDtoByTitle = new BookDto();
        bookDtoByTitle.setId(bookByTitle.getId());
        bookDtoByTitle.setTitle(bookByTitle.getTitle());
        bookDtoByTitle.setAuthor(bookByTitle.getAuthor().getName());
        bookDtoByTitle.setGenre(bookByTitle.getGenre().getType());

        return bookDtoByTitle;
    }

    public Set<BookDto> findAllBooks(){
        Set<Book> allBooks = (Set<Book>) bookRepository.findAll();
        Set<BookDto> allBooksDto = new HashSet<>();

        for (Book book: allBooks) {
            BookDto bookDto = new BookDto();
            bookDto.setId(book.getId());
            bookDto.setGenre(book.getGenre().getType());
            bookDto.setAuthor(book.getAuthor().getName());
            bookDto.setClientNames(String
                    .format(book.getClient().getFirstName()+ " "
                            +book.getClient().getLastName()));
            allBooksDto.add(bookDto);
        }
        return allBooksDto;
    }

    public void safeBook(BookDto bookDto){
        
    }

}

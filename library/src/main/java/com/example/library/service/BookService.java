package com.example.library.service;

import com.example.library.dto.BookDto;
import com.example.library.model.Book;
import com.example.library.repository.BookRepository;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Book findByTitle(@NotNull String title){
        return bookRepository.findByTitle(title)
                .orElseThrow(() -> new NoSuchElementException());
    }

    public Set<BookDto> findAllBooks(){
        Set<Book> allBooks = (Set<Book>) bookRepository.findAll();
        Set<BookDto> allBooksDto = new HashSet<>();

        for (Book book: allBooks) {
            BookDto bookDto = new BookDto();
            bookDto.setId(book.getId());
            bookDto.setGenre(book.getGenre());
            bookDto.setAuthor(book.getAuthor());
            bookDto.setClientNames(String
                    .format(book.getClient().getFirstName()+ " "
                            +book.getClient().getLastName()));
            allBooksDto.add(bookDto);
        }
        return allBooksDto;
    }

    public Book safeBook(Book book){
            return bookRepository.save(book);
    }

    public void removeBook(String title){
        bookRepository.deleteByTitle(title)
                .orElseThrow(()-> new NoSuchElementException(
                        String.format("Book %s does not exist", title)));
    }

}

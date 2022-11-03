package com.squadteam.controller;

import com.squadteam.model.Book;
import com.squadteam.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.Set;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class BookController {

    private final BookRepository bookRepository;

    @QueryMapping
    public Set<Book> getBooks() {
        return bookRepository.findAll();
    }

    @QueryMapping
    public Book getBookById(@Argument UUID id) {
        return bookRepository.findById(id);
    }

    @QueryMapping
    public Set<Book> getBooksByAuthorName(@Argument String authorName) {
        return bookRepository.findBooksByAuthorName(authorName);
    }

    @MutationMapping
    public Book addBook(@Argument final String title, @Argument final int pages, @Argument final UUID authorId) {
        return bookRepository.addBook(title,pages,authorId);
    }

    @MutationMapping
    public Book updateBook(@Argument final UUID id, @Argument final String title, @Argument final int pages) {
        return bookRepository.updateBook(id,title,pages);
    }

    @MutationMapping
    public void deleteBook(@Argument final UUID id) {
        bookRepository.deleteBook(id);
    }
}

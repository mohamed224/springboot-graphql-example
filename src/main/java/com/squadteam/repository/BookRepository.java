package com.squadteam.repository;

import com.squadteam.model.Author;
import com.squadteam.model.Book;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class BookRepository {

    private final AuthorRepository authorRepository;
    private final Set<Book> books = new HashSet<>();

    public BookRepository(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public Book findById(final UUID id) {
        return books.stream()
                .filter(book -> Objects.equals(book.getId(), id))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(String.format("Book with id: %s does not exist",id)));
    }

    public Set<Book> findAll() {
        return books;
    }

    public Set<Book> findBooksByAuthorName(final String authorName) {
        return books.stream()
                .filter(book -> Objects.equals(book.getAuthor().getName(), authorName))
                .collect(Collectors.toSet());
    }

    public Book addBook(final String title, final int pages, final UUID authorId) {
        Author author = authorRepository.findById(authorId);
        return addOrUpdateBook(UUID.randomUUID(),title,pages,author,false);
    }
    public Book updateBook(final UUID id, final String title, final int pages) {
        Author author = books.stream()
                .filter(book -> Objects.equals(book.getId(), id))
                .findFirst()
                .map(Book::getAuthor)
                .orElseThrow(() -> new IllegalArgumentException(String.format("Book with id: %s does not exist",id)));
        return addOrUpdateBook(id,title,pages,author,true);
    }

    public void deleteBook(final UUID id) {
        books.remove(Book.builder().id(id).build());
    }

    private Book addOrUpdateBook(final UUID id, final String title, final int pages, final Author author, final boolean isUpdateAction) {
        Book book = Book.from(id,title,pages,author);
        if(isUpdateAction) {
            books.remove(Book.builder().id(id).build());
        }
        books.add(book);
        return book;
    }


    @PostConstruct
    void initBookData() {
        books.add(new Book(UUID.randomUUID(),"SpringBoot master",250, authorRepository.findById(UUID.fromString("621bdebe-e567-4990-ad81-48f2e1e936c1"))));
        books.add(new Book(UUID.randomUUID(),"Clean Code",300, authorRepository.findById(UUID.fromString("e4e4c067-ea27-4dbd-acbc-a3a007a4b17f"))));
        books.add(new Book(UUID.randomUUID(),"Gang of Four",500, authorRepository.findById(UUID.fromString("e4f86dfa-f985-4025-9362-8f7f9288533b"))));
    }
}

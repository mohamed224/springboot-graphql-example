package com.squadteam.repository;

import com.squadteam.model.Author;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Service
public class AuthorRepository {

    private final Set<Author> authors = new HashSet<>();
    public Author findById(final UUID id) {
        return authors.stream()
                .filter(author -> Objects.equals(author.getId(), id))
                .findFirst()
                .orElseThrow(() -> new RuntimeException(String.format("Author with id: %s does not exist",id)));
    }


    @PostConstruct
    void initAuthorData() {
        authors.add(new Author(UUID.fromString("621bdebe-e567-4990-ad81-48f2e1e936c1"), "Mohamed"));
        authors.add(new Author(UUID.fromString("e4e4c067-ea27-4dbd-acbc-a3a007a4b17f"), "Jean"));
        authors.add(new Author(UUID.fromString("e4f86dfa-f985-4025-9362-8f7f9288533b"), "Massim"));
    }
}

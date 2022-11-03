package com.squadteam.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
public class Author {
    private final UUID id;
    private final String name;

    private final Set<Book> books;

    public Author(final UUID id, final String name) {
        this.id = id;
        this.name = name;
        this.books = new HashSet<>();
    }
}

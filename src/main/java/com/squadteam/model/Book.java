package com.squadteam.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Book {
    private final UUID id;
    private final String title;
    private final int pages;
    private final Author author;

    public static Book from(final UUID id, final String title, final int pages, final Author author) {
        return Book.builder()
                .id(id)
                .title(title)
                .pages(pages)
                .author(author)
                .build();
    }
}

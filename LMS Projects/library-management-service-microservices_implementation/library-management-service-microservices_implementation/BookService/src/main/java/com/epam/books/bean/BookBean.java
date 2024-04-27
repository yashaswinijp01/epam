package com.epam.books.bean;

import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class BookBean {
    private int id;

    @NotNull
    private String name;
    @NotNull
    private String publisher;
    @NotNull
    private String author;

    public BookBean(String name, String publisher, String author) {
        this.name = name;
        this.publisher = publisher;
        this.author = author;
    }
}

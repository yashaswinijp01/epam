package com.epam.books.bean;

import javax.validation.constraints.NotNull;

public class BookBean {
    private int id;

    @NotNull
    private String name;
    @NotNull
    private String publisher;
    @NotNull
    private String author;

    public BookBean() {
    }

    public BookBean(String name, String publisher, String author) {
        this.name = name;
        this.publisher = publisher;
        this.author = author;
    }

    public BookBean(int id, String name, String publisher, String author) {
        this.id = id;
        this.name = name;
        this.publisher = publisher;
        this.author = author;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}

package com.epam.library.entity;

import javax.persistence.*;

@Entity
@Table(name = "library")
public class Library {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "username")
    private String username;

    @Column(name = "book_id")
    private int bookId;

    public Library() {}

    public Library(int id, String username, int bookId) {
        this.id = id;
        this.username = username;
        this.bookId = bookId;
    }

    public Library(String username, int bookId) {
        this.username = username;
        this.bookId = bookId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    @Override
    public String toString() {
        return "Library{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", bookId=" + bookId +
                '}';
    }
}

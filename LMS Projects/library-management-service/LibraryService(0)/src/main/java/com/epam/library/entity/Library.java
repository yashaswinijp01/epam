package com.epam.library.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
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



    public Library(String username, int bookId) {
        this.username = username;
        this.bookId = bookId;
    }

}

package com.epam.books.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Book")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Book {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="book_id")
	private int id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "publisher")
	private String publisher;
	
	@Column(name = "author")
	private String author;

	public Book(String name, String publisher, String author) {
		this.name = name;
		this.publisher = publisher;
		this.author = author;
	}
}

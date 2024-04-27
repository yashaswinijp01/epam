package com.epam.books.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Book")
public class Book {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "publisher")
	private String publisher;
	
	@Column(name = "author")
	private String author;

	public Book() {
	}

	public Book(String name, String publisher, String author) {
		this.name = name;
		this.publisher = publisher;
		this.author = author;
	}

	public Book(int id, String name, String publisher, String author) {
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

	@Override
	public String toString() {
		return "Book{" +
				"id=" + id +
				", name='" + name + '\'' +
				", publisher='" + publisher + '\'' +
				", author='" + author + '\'' +
				'}';
	}
}

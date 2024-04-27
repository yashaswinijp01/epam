package com.epam.bean;

import lombok.*;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;


@NoArgsConstructor
@Getter
@Setter
@ToString
@Component
public class BookBean {

	private int id;

	@NotNull
	@Size(min = 3,max = 20,message = "{NotNull.bookBean.name}")
	private String name;

	@NotNull
	@Size(min = 3,max = 40)
	private String publisher;

	@NotNull
	@Size(min = 3,max = 40)
	private String author;

	public BookBean(int id, String name, String publisher, String author) {
		this.id = id;
		this.name = name;
		this.publisher = publisher;
		this.author = author;
	}

	public BookBean(String name, String publisher, String author) {
		this.name = name;
		this.publisher = publisher;
		this.author = author;
	}


}

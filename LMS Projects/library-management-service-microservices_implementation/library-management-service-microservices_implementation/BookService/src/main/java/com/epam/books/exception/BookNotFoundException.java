package com.epam.books.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BookNotFoundException extends RuntimeException{
    public BookNotFoundException(){}

    public BookNotFoundException(String message){
        super(message);
    }
}

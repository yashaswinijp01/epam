package com.epam.books.controller;

import com.epam.books.bean.BookBean;
import com.epam.books.entity.Book;
import com.epam.books.exception.BookNotFoundException;
import com.epam.books.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class BookController {

    @Autowired
    BookService bookService;

    @GetMapping("/books")
    public ResponseEntity<List<Book>> getBooks() {
        return new ResponseEntity<>(bookService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/books/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable("id") int id){
        Book book = bookService.findById(id);
        if (book == null)
            throw new BookNotFoundException("Book not found");
        return new ResponseEntity<>(book, HttpStatus.OK);
    }

    @PostMapping(value = "/books", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Book> addBook(@RequestBody @Valid BookBean bookBean){
        return new ResponseEntity<>(bookService.save(bookBean), HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/books/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Book> deleteBook(@PathVariable("id") int id){
        Book book = bookService.deleteById(id);
        if (book == null)
            throw new BookNotFoundException("Book not found");
        return new ResponseEntity<>(book, HttpStatus.OK);
    }

    @PutMapping(value = "/books/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Book> updateBook(@PathVariable("id") int id, @RequestBody @Valid BookBean bookBean){
        Book updatedBook = bookService.updateBook(id, bookBean);
        if (updatedBook == null)
            throw new BookNotFoundException("Book Not Found");
        return new ResponseEntity<>(updatedBook, HttpStatus.OK);
    }

}

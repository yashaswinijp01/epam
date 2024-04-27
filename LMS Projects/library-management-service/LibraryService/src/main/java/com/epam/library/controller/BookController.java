package com.epam.library.controller;

import com.epam.library.bean.BookBean;
import com.epam.library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookController {

    @Autowired
    BookService bookService;

    @GetMapping("/library/books")
    public ResponseEntity<List<BookBean>> getAllBooksFromLibrary() {
        List<BookBean> bookBeans = bookService.getAllBooksFromLibrary();
        return new ResponseEntity<>(bookBeans, HttpStatus.OK);
    }

    @GetMapping("/library/books/{bookId}")
    public ResponseEntity<BookBean> getBookByBookId(@PathVariable("bookId") int id) {
        BookBean bookBean = bookService.getBookById(id);
        return new ResponseEntity<>(bookBean, HttpStatus.OK);
    }

    @PostMapping("/library/books")
    public ResponseEntity<BookBean> addBook(@RequestBody BookBean bookBean) {
        BookBean bean = bookService.addBook(bookBean);
        return new ResponseEntity<>(bean, HttpStatus.CREATED);
    }

    @PutMapping("/library/books/{bookId}")
    public ResponseEntity<BookBean> updateBookById(@RequestBody BookBean bookBean, @PathVariable("bookId") int id) {
        BookBean bean = bookService.updateBookById(bookBean, id);
        return new ResponseEntity<>(bean, HttpStatus.OK);
    }

    @DeleteMapping("/library/books/{bookId}")
    public ResponseEntity<BookBean> deleteBookById(@PathVariable("bookId") int id) {
        BookBean bean = bookService.deleteBookById(id);
        return new ResponseEntity<>(bean, HttpStatus.OK);
    }
}

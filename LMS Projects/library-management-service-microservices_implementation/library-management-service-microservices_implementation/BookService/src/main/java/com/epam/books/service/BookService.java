package com.epam.books.service;

import com.epam.books.bean.BookBean;
import com.epam.books.entity.Book;

import java.util.List;

public interface BookService {
    Book save(BookBean bookBean);
    List<Book> findAll();
    Book findById(int id);
    Book deleteById(int id);
    Book updateBook(int id, BookBean bookBean);
}

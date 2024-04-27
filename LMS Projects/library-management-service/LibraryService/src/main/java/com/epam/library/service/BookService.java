package com.epam.library.service;

import com.epam.library.bean.BookBean;

import java.util.List;

public interface BookService {
    List<BookBean> getAllBooksFromLibrary();
    BookBean getBookById(int id);
    BookBean addBook(BookBean bookBean);
    BookBean updateBookById(BookBean bookBean, int id);
    BookBean deleteBookById(int id);
}

package com.epam.books.service;

import com.epam.books.bean.BookBean;
import com.epam.books.dao.BookDaoWrapper;
import com.epam.books.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService{
    @Autowired
    private BookDaoWrapper bookDaoWrapper;

    @Override
    public Book save(BookBean bookBean) {

        return bookDaoWrapper.save(bookBean);
    }

    @Override
    public List<Book> findAll() {
        return bookDaoWrapper.findAll();
    }

    @Override
    public Book findById(int id) {
        return bookDaoWrapper.findById(id);
    }

    @Override
    public Book deleteById(int id) {

        return bookDaoWrapper.deleteById(id);
    }

    @Override
    public Book updateBook(int id, BookBean bookBean) {
        return bookDaoWrapper.updateById(id, bookBean);
    }
}

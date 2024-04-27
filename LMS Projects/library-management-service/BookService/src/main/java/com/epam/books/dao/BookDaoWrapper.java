package com.epam.books.dao;

import com.epam.books.bean.BookBean;
import com.epam.books.entity.Book;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class BookDaoWrapper {
    @Autowired
    BookDao bookDao;

    public Book save(BookBean bookBean){
        Book book = convertBeanToEntity(bookBean);
        return bookDao.save(book);
    }

    private Book convertBeanToEntity(BookBean bookBean) {
        Book book = new Book();
        BeanUtils.copyProperties(bookBean, book);
        return book;
    }

    public List<Book> findAll(){
        return bookDao.findAll();
    }

    public Book findById(int id){
        Book book;
        Optional<Book> optionalBook = bookDao.findById(id);
        book = optionalBook.orElse(null);
        return book;
    }

    public Book deleteById(int id){
        Book book = findById(id);
        if (book == null)
            return null;
        bookDao.deleteById(id);
        return book;
    }

    public Book updateById(int id, BookBean bookBean){
        Book bookToBeUpdated = findById(id);
        if (bookToBeUpdated == null)
            return null;
        bookToBeUpdated.setAuthor(bookBean.getAuthor());
        bookToBeUpdated.setName(bookBean.getName());
        bookToBeUpdated.setPublisher(bookBean.getPublisher());
        bookDao.save(bookToBeUpdated);
        return bookToBeUpdated;
    }
}

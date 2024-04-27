package com.epam.books.dao;

import com.epam.books.bean.BookBean;
import com.epam.books.entity.Book;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
 class BookDaoWrapperTest {

    @InjectMocks
    BookDaoWrapper bookDaoWrapper;

    @Mock
    BookDao bookDao;

    @Test
    void save() {
        Book book = new Book("name", "pub", "Aut");
        Mockito.when(bookDao.save(Mockito.any())).thenReturn(book);
        Book book1 = bookDaoWrapper.save(new BookBean());
        Assertions.assertEquals(book.getAuthor(), book1.getAuthor());
    }

    @Test
    void findAll() {
        Book book = new Book("name", "pub", "Aut");
        List<Book> books = List.of(book);
        Mockito.when(bookDao.findAll()).thenReturn(books);
        List<Book> bookList = bookDaoWrapper.findAll();
        Assertions.assertEquals(1, bookList.size());
    }

    @Test
    void findById() {
        Optional<Book> book = Optional.of(new Book("name", "pub", "Aut"));
        Mockito.when(bookDao.findById(Mockito.anyInt())).thenReturn(book);
        Book book1 = bookDaoWrapper.findById(1);
        Assertions.assertEquals(book.get().getAuthor(), book1.getAuthor());
        Assertions.assertEquals(book.get().getName(), book1.getName());
        Assertions.assertEquals(book.get().getPublisher(), book1.getPublisher());
    }

    @Test
    void deleteById() {
        Optional<Book> book = Optional.of(new Book("name", "pub", "Aut"));
        Mockito.when(bookDao.findById(Mockito.anyInt())).thenReturn(book);
        Book book1 = bookDaoWrapper.deleteById(1);
        Assertions.assertEquals(book.get().getAuthor(), book1.getAuthor());
        Assertions.assertEquals(book.get().getName(), book1.getName());
        Assertions.assertEquals(book.get().getPublisher(), book1.getPublisher());
    }

    @Test
    void updateById() {
        Optional<Book> book = Optional.of(new Book("name", "pub", "Aut"));
        Mockito.when(bookDao.findById(Mockito.anyInt())).thenReturn(book);
        Book book1 = bookDaoWrapper.updateById(1, new BookBean());
        Assertions.assertEquals(book.get().getAuthor(), book1.getAuthor());
        Assertions.assertEquals(book.get().getName(), book1.getName());
        Assertions.assertEquals(book.get().getPublisher(), book1.getPublisher());
    }

}

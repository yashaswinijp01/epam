package com.epam.books.service;

import com.epam.books.bean.BookBean;
import com.epam.books.dao.BookDaoWrapper;
import com.epam.books.entity.Book;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;


@ExtendWith(MockitoExtension.class)
class BookServiceTests {

    @InjectMocks
    BookServiceImpl bookService;

    @Mock
    BookDaoWrapper bookDaoWrapper;

    @Test
    void getAllBooks() {
        Book book1 = new Book(201, "Core Java", "Ajay", "Anu");
        Book book2 = new Book(202, "Advance Java", "Arun", "Anup");
        List<Book> books = Arrays.asList(book1, book2);
        Mockito.when(bookDaoWrapper.findAll()).thenReturn(books);
        Assertions.assertEquals(2, bookService.findAll().size());
        //Mockito.verify(bookDaoWrapper, Mockito.times(1)).findAll();
    }

    @Test
    void getBookById() {
        //int id=62;
        Book book = new Book(62, "Java", "Arun", "Ajay");
        Mockito.when(bookDaoWrapper.findById(Mockito.anyInt())).thenReturn(book);

        Book newBook = bookService.findById(62);

        Assertions.assertEquals("Java", newBook.getName());
        Assertions.assertEquals("Arun", newBook.getPublisher());
        Assertions.assertEquals("Ajay", newBook.getAuthor());
    }

    @Test
    void getBookByIdWhenIdNotAvailable() {
        Mockito.when(bookDaoWrapper.findById(Mockito.anyInt())).thenReturn(null);
        Book newBook = bookService.findById(8);
        Assertions.assertNull(newBook);
    }

    @Test
    void addBook() {
        Book book = new Book(5, "python", "Raj", "Raju");
        Mockito.when(bookDaoWrapper.save(Mockito.any())).thenReturn(book);

        Book savedBook = bookService.save(new BookBean());

        Assertions.assertEquals("python", savedBook.getName());
        Assertions.assertEquals("Raj", savedBook.getPublisher());
        Assertions.assertEquals("Raju", savedBook.getAuthor());
    }

    @Test
    void deleteBook() {
        Book book1 = new Book(5,"python","Raj","Raju");
        bookService.deleteById(5);
        verify(bookDaoWrapper,times(1)).deleteById(5);
    }

    @Test
    void deleteBookWhenIdNotAvailable() {
        Mockito.when(bookDaoWrapper.deleteById(Mockito.anyInt())).thenReturn(null);
        Book deletedBook = bookService.deleteById(1);
        Assertions.assertNull(deletedBook);
    }

    @Test
    void updateBookById() {
        Book book = new Book(301, "python", "adam", "arun");
        Mockito.when(bookDaoWrapper.updateById(Mockito.anyInt(), Mockito.any())).thenReturn(book);
        Book updatedBook = bookService.updateBook(1, new BookBean());
        Assertions.assertEquals("python", updatedBook.getName());
        Assertions.assertEquals("adam", updatedBook.getPublisher());
        Assertions.assertEquals("arun", updatedBook.getAuthor());
    }

    @Test
    void updateBookWhenIdNotAvailable() {
        Mockito.when(bookDaoWrapper.updateById(Mockito.anyInt(), Mockito.any())).thenReturn(null);
        Book updatedBook = bookService.updateBook(5, new BookBean());
        Assertions.assertNull(updatedBook);
    }
}

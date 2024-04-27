package com.epam.books.service;

import com.epam.books.bean.BookBean;
import com.epam.books.dao.BookDao;
import com.epam.books.dao.BookDaoWrapper;
import com.epam.books.entity.Book;
import com.epam.books.exception.BookNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class BookServiceTests {

    @InjectMocks
    BookServiceImpl bookService;

    @Mock
    BookDaoWrapper bookDaoWrapper;

    @Mock
    BookDao bookDao;

    BookBean bookBean;

    @Test
    void getAllBooks() {
        Book book1 = new Book(1, "Core Java", "Akash", "Adam");
        Book book2 = new Book(2, "Advance Java", "raj", "Ravi");
        Book book3 = new Book(3, "Spring Jpa", "Ravi", "Rajesh");
        List<Book> books = Arrays.asList(book1, book2, book3);
        Mockito.when(bookDaoWrapper.findAll()).thenReturn(books);
        List<Book> result = bookService.findAll();
        Assertions.assertEquals(3, result.size());
        Mockito.verify(bookDaoWrapper, Mockito.times(1)).findAll();
    }

    @Test
    void getBookById() {
        Book book = new Book(1, "Core Java", "Akash", "Adam");
        Mockito.when(bookDaoWrapper.findById(Mockito.anyInt(), bookBean)).thenReturn(book);
        Book newBook = bookService.findById(1);
        Assertions.assertEquals("Core Java", newBook.getName());
        Assertions.assertEquals("Akash", newBook.getPublisher());
        Assertions.assertEquals("Adam", newBook.getAuthor());
    }

    @Test
    void getBookByIdWhenIdNotAvailable() {
        Mockito.when(bookDaoWrapper.findById(Mockito.anyInt(), bookBean)).thenReturn(null);

        Book newBook = bookService.findById(1);

        Assertions.assertNull(newBook);
    }

    @Test
    void addBook() {
        Book book = new Book(1, "AI", "Jhon", "Anand");
        Mockito.when(bookDaoWrapper.save(Mockito.any())).thenReturn(book);

        Book savedBook = bookService.save(new BookBean());

        Assertions.assertEquals("AI", savedBook.getName());
        Assertions.assertEquals("Jhon", savedBook.getPublisher());
        Assertions.assertEquals("Anand", savedBook.getAuthor());
    }

    @Test
    void deleteBook() {
        Book book = new Book(1, "Book", "Pub", "Aut");
        Mockito.when(bookDaoWrapper.deleteById(Mockito.anyInt())).thenReturn(book);

        Book deletedBook = bookService.deleteById(1);

        Assertions.assertEquals("Book", deletedBook.getName());
        Assertions.assertEquals("Pub", deletedBook.getPublisher());
        Assertions.assertEquals("Aut", deletedBook.getAuthor());
    }

    @Test
    void deleteBookWhenIdNotAvailable() {
        Mockito.when(bookDaoWrapper.deleteById(Mockito.anyInt())).thenReturn(null);

        Book deletedBook = bookService.deleteById(1);

        Assertions.assertNull(deletedBook);
    }

    @Test
    void updateBookById() {
        Book book = new Book(1, "Book", "Pub", "Aut");
        Mockito.when(bookDaoWrapper.updateById(Mockito.anyInt(), Mockito.any())).thenReturn(book);

        Book updatedBook = bookService.updateBook(1, new BookBean());

        Assertions.assertEquals("Book", updatedBook.getName());
        Assertions.assertEquals("Pub", updatedBook.getPublisher());
        Assertions.assertEquals("Aut", updatedBook.getAuthor());
    }

    @Test
    void updateBookWhenIdNotAvailable() {
        Mockito.when(bookDaoWrapper.updateById(Mockito.anyInt(), Mockito.any())).thenReturn(null);

        Book updatedBook = bookService.updateBook(1, new BookBean());

        Assertions.assertNull(updatedBook);
    }

    @Test
    void findById(){

    }

    @Test
    @DisplayName("getAccountById should throw exception if account not found")
    public void findByIdShouldThrowExceptionIfBookNotFound(){

    }


}

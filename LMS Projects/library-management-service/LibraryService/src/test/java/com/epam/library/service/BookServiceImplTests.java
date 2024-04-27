package com.epam.library.service;

import com.epam.library.bean.BookBean;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith({MockitoExtension.class})
class BookServiceImplTests {

    @InjectMocks
    BookServiceImpl bookService;

    @Mock
    RestTemplate restTemplate;

    @Test
    void getAllBooksFromLibrary() {
        BookBean book = new BookBean(1, "Book1", "Pub1", "Aut1");
        List<BookBean> books = List.of(book);

        ResponseEntity<List<BookBean>> serviceResponse =
                new ResponseEntity<>(books, HttpStatus.OK);

        Mockito.when(restTemplate.exchange(
                ArgumentMatchers.anyString(),
                ArgumentMatchers.any(HttpMethod.class),
                ArgumentMatchers.any(),
                ArgumentMatchers.<ParameterizedTypeReference<List<BookBean>>>any()))
                .thenReturn(serviceResponse);

        List<BookBean> response = bookService.getAllBooksFromLibrary();
        Assertions.assertNotNull(response);
    }

    @Test
    void getBookById() {
        BookBean book = new BookBean(1, "Book1", "Pub1", "Aut1");

        ResponseEntity<BookBean> serviceResponse =
                new ResponseEntity<>(book, HttpStatus.OK);

        Mockito.when(restTemplate.exchange(
                        ArgumentMatchers.anyString(),
                        ArgumentMatchers.any(HttpMethod.class),
                        ArgumentMatchers.any(),
                        ArgumentMatchers.<Class<BookBean>>any()))
                .thenReturn(serviceResponse);

        BookBean response = bookService.getBookById(1);
        Assertions.assertNotNull(response);
    }

    @Test
    void addBook() {
        BookBean book = new BookBean(1, "Book1", "Pub1", "Aut1");

        ResponseEntity<BookBean> serviceResponse =
                new ResponseEntity<>(book, HttpStatus.OK);

        Mockito.when(restTemplate.exchange(
                        ArgumentMatchers.anyString(),
                        ArgumentMatchers.any(HttpMethod.class),
                        ArgumentMatchers.any(),
                        ArgumentMatchers.<Class<BookBean>>any()))
                .thenReturn(serviceResponse);

        BookBean response = bookService.addBook(book);
        Assertions.assertNotNull(response);
    }

    @Test
    void updateBookById() {
        BookBean book = new BookBean(1, "Book1", "Pub1", "Aut1");

        ResponseEntity<BookBean> serviceResponse =
                new ResponseEntity<>(book, HttpStatus.OK);

        Mockito.when(restTemplate.exchange(
                        ArgumentMatchers.anyString(),
                        ArgumentMatchers.any(HttpMethod.class),
                        ArgumentMatchers.any(),
                        ArgumentMatchers.<Class<BookBean>>any()))
                .thenReturn(serviceResponse);

        BookBean response = bookService.updateBookById(new BookBean(),1);
        System.out.println(response);
        Assertions.assertNotNull(response);
    }

    @Test
    void deleteBookById() {
        BookBean book = new BookBean(1, "Book1", "Pub1", "Aut1");

        ResponseEntity<BookBean> serviceResponse =
                new ResponseEntity<>(book, HttpStatus.OK);

        Mockito.when(restTemplate.exchange(
                        ArgumentMatchers.anyString(),
                        ArgumentMatchers.any(HttpMethod.class),
                        ArgumentMatchers.any(),
                        ArgumentMatchers.<Class<BookBean>>any()))
                .thenReturn(serviceResponse);

        BookBean response = bookService.deleteBookById(1);
        System.out.println(response);
        Assertions.assertNotNull(response);
    }
}
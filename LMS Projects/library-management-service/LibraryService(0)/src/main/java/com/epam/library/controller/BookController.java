package com.epam.library.controller;

import com.epam.library.bean.BookBean;
import com.epam.library.entity.Library;
import com.epam.library.exception.BookNotFoundException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

@RestController
public class BookController {

    @Autowired
    RestTemplate restTemplate;

    private static final String BOOK_URI = "http://localhost:9090/BooksPortal/";

    @GetMapping("/library/books")
    public ResponseEntity<List<BookBean>> getAllBooksFromLibrary() {
        ResponseEntity<List> responseEntity = restTemplate.exchange(BOOK_URI + "books", HttpMethod.GET,null,List.class);
        List<LinkedHashMap<String, Object>> bookList = responseEntity.getBody();
        ObjectMapper objectMapper = new ObjectMapper();
        List<BookBean> bookBeans = new ArrayList<>();
        if (bookList != null) {
            for (LinkedHashMap<String, Object> map: bookList) {
                BookBean bookBean = objectMapper.convertValue(map, BookBean.class);
                bookBeans.add(bookBean);
            }
        }
        return new ResponseEntity<>(bookBeans, HttpStatus.OK);
    }

    @GetMapping("/library/books/{bookId}")
    public ResponseEntity<BookBean> getBookByBookId(@PathVariable("bookId") int id) {
        restTemplate.setErrorHandler(new ResponseErrorHandler() {
            @Override
            public boolean hasError(ClientHttpResponse response) throws IOException {
                return response.getStatusCode() == HttpStatus.BAD_REQUEST;
            }

            @Override
            public void handleError(ClientHttpResponse response) throws IOException {
                throw new BookNotFoundException("Book not found");
            }
        });
        ResponseEntity<BookBean> responseEntity = restTemplate.exchange(BOOK_URI + "books/" + id, HttpMethod.GET,null,BookBean.class);
        BookBean bookBean = responseEntity.getBody();
        return new ResponseEntity<>(bookBean, HttpStatus.OK);
    }

    @PostMapping("/library/books")
    public ResponseEntity<BookBean> addBook(@RequestBody BookBean bookBean) {
        HttpEntity<BookBean> entity = new HttpEntity<>(bookBean);
        ResponseEntity<BookBean> responseEntity = restTemplate.exchange(BOOK_URI + "books", HttpMethod.POST, entity, BookBean.class);
        BookBean bean = responseEntity.getBody();
        return new ResponseEntity<>(bean, HttpStatus.CREATED);
    }

    @PutMapping("/library/books/{bookId}")
    public ResponseEntity<BookBean> updateBookById(@RequestBody BookBean bookBean, @PathVariable("bookId") int id) {
        restTemplate.setErrorHandler(new ResponseErrorHandler() {
            @Override
            public boolean hasError(ClientHttpResponse response) throws IOException {
                return response.getStatusCode() == HttpStatus.BAD_REQUEST;
            }

            @Override
            public void handleError(ClientHttpResponse response) throws IOException {
                throw new BookNotFoundException("Book not found");
            }
        });
        HttpEntity<BookBean> entity = new HttpEntity<>(bookBean);
        ResponseEntity<BookBean> responseEntity = restTemplate.exchange(BOOK_URI + "books/" + id, HttpMethod.PUT, entity, BookBean.class);
        BookBean bean = responseEntity.getBody();
        return new ResponseEntity<>(bean, HttpStatus.OK);
    }

    @DeleteMapping("/library/books/{bookId}")
    public ResponseEntity<BookBean> deleteBookById(@PathVariable("bookId") int id) {
        restTemplate.setErrorHandler(new ResponseErrorHandler() {
            @Override
            public boolean hasError(ClientHttpResponse response) throws IOException {
                return response.getStatusCode() == HttpStatus.BAD_REQUEST;
            }

            @Override
            public void handleError(ClientHttpResponse response) throws IOException {
                throw new BookNotFoundException("Book not found");
            }
        });
        ResponseEntity<BookBean> responseEntity = restTemplate.exchange(BOOK_URI + "books/" + id, HttpMethod.DELETE, null, BookBean.class);
        BookBean bean = responseEntity.getBody();
        return new ResponseEntity<>(bean, HttpStatus.OK);
    }
}

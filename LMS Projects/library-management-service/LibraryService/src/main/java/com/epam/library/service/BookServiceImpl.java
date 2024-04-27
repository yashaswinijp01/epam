package com.epam.library.service;

import com.epam.library.bean.BookBean;
import com.epam.library.exception.BookNotFoundException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

@Service
public class BookServiceImpl implements BookService{

    @Autowired
    RestTemplate restTemplate;

    private static final String BOOK_URI = "http://localhost:9401/BooksPortal/";


    @Override
    public List<BookBean> getAllBooksFromLibrary() {
        ResponseEntity<List<BookBean>> responseEntity = restTemplate.exchange(BOOK_URI + "books", HttpMethod.GET,null,new ParameterizedTypeReference<List<BookBean>>() {});
        List<BookBean> bookBeans = new ArrayList<>();
        for (BookBean bean: responseEntity.getBody()){
            bookBeans.add(bean);
        }
        return bookBeans;
    }

    @Override
    public BookBean getBookById(int id) {
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
        return bookBean;
    }

    @Override
    public BookBean addBook(BookBean bookBean) {
        HttpEntity<BookBean> entity = new HttpEntity<>(bookBean);
        ResponseEntity<BookBean> responseEntity = restTemplate.exchange(BOOK_URI + "books", HttpMethod.POST, entity, BookBean.class);
        BookBean bean = responseEntity.getBody();
        return bean;
    }

    @Override
    public BookBean updateBookById(BookBean bookBean, int id) {
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
        System.out.println(responseEntity.getStatusCode());
        BookBean bean = responseEntity.getBody();
        return bean;
    }

    @Override
    public BookBean deleteBookById(int id) {
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
        return bean;
    }
}

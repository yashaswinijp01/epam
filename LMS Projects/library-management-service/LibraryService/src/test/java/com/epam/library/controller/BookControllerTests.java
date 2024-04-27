package com.epam.library.controller;

import com.epam.library.bean.BookBean;
import com.epam.library.service.BookServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

@ExtendWith(SpringExtension.class)
@WebMvcTest(BookController.class)
class BookControllerTests {
    @MockBean
    BookServiceImpl bookService;

    @Autowired
    MockMvc mockMvc;

    @Test
    void getAllBooksFromLibrary() throws Exception {
        BookBean book = new BookBean(1, "Book1", "Pub1", "Aut1");
        List<BookBean> books = List.of(book);
        Mockito.when(bookService.getAllBooksFromLibrary()).thenReturn(books);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/library/books")
                .accept(MediaType.APPLICATION_JSON_VALUE);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        String expected = "[" +
                "    {" +
                "        id: 1," +
                "        name: Book1," +
                "        publisher: Pub1," +
                "        author: Aut1" +
                "    }]" ;
        JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
        Assertions.assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());
    }

    @Test
    void getBookByBookId() throws Exception {
        BookBean book = new BookBean(2, "Core Java", "Public", "Anu");
        Mockito.when(bookService.getBookById(Mockito.anyInt())).thenReturn(book);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/library/books/1")
                .accept(MediaType.APPLICATION_JSON_VALUE);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        String expected = "{id: 2,name: Core Java,publisher: Public,author: Anu}" ;
        JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
        Assertions.assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());
    }

    @Test
    void addBook() throws Exception {
        BookBean book = new BookBean(1, "Book1", "Pub1", "Aut1");
        Mockito.when(bookService.addBook(Mockito.any())).thenReturn(book);
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/library/books")
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .content("{\"name\":\"Book1\", \"publisher\": \"Pub1\",\"author\": \"Aut1\"}")
                .contentType(MediaType.APPLICATION_JSON_VALUE);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        Assertions.assertEquals(HttpStatus.CREATED.value(), response.getStatus());
        String expected = "{id: 1,name: Book1,publisher: Pub1,author: Aut1}" ;
        JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
    }

    @Test
    void updateBookById() throws Exception {
        BookBean book = new BookBean(1, "Book1", "Pub1", "Aut1");
        Mockito.when(bookService.updateBookById(Mockito.any(), Mockito.anyInt())).thenReturn(book);
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .put("/library/books/1")
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .content("{\"name\":\"Book1\", \"publisher\": \"Pub1\",\"author\": \"Aut1\"}")
                .contentType(MediaType.APPLICATION_JSON_VALUE);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        Assertions.assertEquals(HttpStatus.OK.value(), response.getStatus());
        String expected = "{id: 1,name: Book1,publisher: Pub1,author: Aut1}" ;
        JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
    }

    @Test
    void updateBookByIdWhenBookIsNotAvailable() throws Exception {
        Mockito.when(bookService.updateBookById(Mockito.any(), Mockito.anyInt())).thenReturn(null);
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .put("/library/books/1")
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .content("{\"name\":\"Book1\", \"publisher\": \"Pub1\",\"author\": \"Aut1\"}")
                .contentType(MediaType.APPLICATION_JSON_VALUE);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        Assertions.assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

    @Test
    void deleteBookByIdWhenBookIsNotAvailable() throws Exception {
        Mockito.when(bookService.deleteBookById(Mockito.anyInt())).thenReturn(null);
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .delete("/library/books/1")
                .accept(MediaType.APPLICATION_JSON_VALUE);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        Assertions.assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

    @Test
    void deleteBookById() throws Exception {
        BookBean book = new BookBean(1, "Book1", "Pub1", "Aut1");
        Mockito.when(bookService.deleteBookById(Mockito.anyInt())).thenReturn(book);
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .delete("/library/books/1")
                .accept(MediaType.APPLICATION_JSON_VALUE);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        Assertions.assertEquals(HttpStatus.OK.value(), response.getStatus());
        String expected = "{id: 1,name: Book1,publisher: Pub1,author: Aut1}" ;
        JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
    }
}
package com.epam.books.controller;

import com.epam.books.entity.Book;
import com.epam.books.service.BookService;
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

import java.util.Arrays;
import java.util.List;

@ExtendWith(SpringExtension.class)
@WebMvcTest(BookController.class)
class BookControllerTests {

    @MockBean
    BookService bookService;

    @Autowired
    MockMvc mockMvc;

    @Test
    void getAllBooksTest() throws Exception {
        Book book1 = new Book(101, "Book1", "Pub1", "Aut1");
        Book book2 = new Book(202, "Book2", "Pub2", "Aut2");
        Book book3 = new Book(303, "Book3", "Pub3", "Aut3");
        List<Book> books =Arrays.asList(book1, book2, book3);
        Mockito.when(bookService.findAll()).thenReturn(books);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/books")
                .accept(MediaType.APPLICATION_JSON_VALUE);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        String expected = "[" +
                "    {" +
                "        id: 101," +
                "        name: Book1," +
                "        publisher: Pub1," +
                "        author: Aut1" +
                "    }," +
                "    {" +
                "        id: 202," +
                "        name: Book2," +
                "        publisher: Pub2," +
                "        author: Aut2" +
                "    }," +
                "    {" +
                "        id: 303," +
                "        name: Book3," +
                "        publisher: Pub3," +
                "        author: Aut3" +
                "    }]" ;
        JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
    }

    @Test
    void getBookByIdWhenBookIsAvailableTest() throws Exception {
        Book book = new Book(501, "Core java", "Raj", "Ram");
        Mockito.when(bookService.findById(Mockito.anyInt())).thenReturn(book);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/books/1")
                .accept(MediaType.APPLICATION_JSON_VALUE);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        Assertions.assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());
        String expected = "{id: 501,name: Core Java,publisher:Raj,author: Ram}" ;
        JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
    }

    @Test
    void getBookByIdWhenBookIsNotAvailableTest() throws Exception {
        Mockito.when(bookService.findById(Mockito.anyInt())).thenReturn(null);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/books/501")
                .accept(MediaType.APPLICATION_JSON_VALUE);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        Assertions.assertEquals(HttpStatus.BAD_REQUEST.value(), result.getResponse().getStatus());
    }

    @Test
    void addBook() throws Exception {
        Book book = new Book(22, "AI", "jhon", "hari");
        Mockito.when(bookService.save(Mockito.any())).thenReturn(book);
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/books")
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .content("{\"name\":\"AI\", \"publisher\": \"jhon\",\"author\": \"hari\"}")
                .contentType(MediaType.APPLICATION_JSON_VALUE);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        Assertions.assertEquals(HttpStatus.CREATED.value(), response.getStatus());
        String expected = "{id: 1,name: Book1,publisher: Pub1,author: Aut1}" ;
        JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
    }

    @Test
    void updateBookByIdWhenBookIsAvailable() throws Exception {
        Book book = new Book(22, "AI", "jhon", "hari");
        Mockito.when(bookService.updateBook(Mockito.anyInt(), Mockito.any())).thenReturn(book);
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .put("/books/22")
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .content("{\"name\":\"AI\", \"publisher\": \"jhon\",\"author\": \"hari\"}")
                .contentType(MediaType.APPLICATION_JSON_VALUE);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        Assertions.assertEquals(HttpStatus.OK.value(), response.getStatus());
        String expected = "{id: 22,name: AI,publisher: jhon,author: hari}" ;
        JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
    }

    @Test
    void updateBookByIdWhenBookIsNotAvailable() throws Exception {
        Mockito.when(bookService.updateBook(Mockito.anyInt(), Mockito.any())).thenReturn(null);
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .put("/books/1")
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .content("{\"name\":\"Book1\", \"publisher\": \"Pub1\",\"author\": \"Aut1\"}")
                .contentType(MediaType.APPLICATION_JSON_VALUE);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        Assertions.assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());
    }

    @Test
    void deleteBookByIdWhenBookIsAvailable() throws Exception {
        Book book = new Book(22, "AI", "jhon", "hari");
        Mockito.when(bookService.deleteById(Mockito.anyInt())).thenReturn(book);
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .delete("/books/1")
                .accept(MediaType.APPLICATION_JSON_VALUE);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        Assertions.assertEquals(HttpStatus.OK.value(), response.getStatus());
        String expected = "{id: 22,name: AI,publisher: jhon,author: hari}" ;
        JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
    }

    @Test
    void deleteBookByIdWhenBookIsNotAvailable() throws Exception {
        Mockito.when(bookService.deleteById(Mockito.anyInt())).thenReturn(null);
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .delete("/books/1")
                .accept(MediaType.APPLICATION_JSON_VALUE);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        Assertions.assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());
    }
}

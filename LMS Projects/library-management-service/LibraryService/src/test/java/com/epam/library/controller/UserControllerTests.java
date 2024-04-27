package com.epam.library.controller;

import com.epam.library.bean.UserBean;
import com.epam.library.entity.Library;
import com.epam.library.service.LibraryServiceImpl;
import com.epam.library.service.UserServiceImpl;
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

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(UserController.class)
class UserControllerTests {
    @MockBean
    UserServiceImpl userService;

    @MockBean
    LibraryServiceImpl libraryService;

    @Autowired
    MockMvc mockMvc;

    @Test
    void getUsers() throws Exception {
        UserBean user1 = new UserBean(1, "user1", "Use1@test.com", "User1");
        List<UserBean> users = Arrays.asList(user1);
        Mockito.when(userService.getUsers()).thenReturn(users);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/library/users")
                .accept(MediaType.APPLICATION_JSON_VALUE);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        String expected = "[" +
                "    {" +
                "        id: 1," +
                "        username: user1," +
                "        email: Use1@test.com," +
                "        name: User1" +
                "    }]" ;
        JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);

    }

    @Test
    void getUserById() throws Exception {
        UserBean user = new UserBean(1, "user1", "user1@test.com", "User1");
        Mockito.when(userService.getUserByUsername(Mockito.anyString())).thenReturn(user);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/library/users/user1")
                .accept(MediaType.APPLICATION_JSON_VALUE);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        Assertions.assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());
        String expected = "{id: 1,username: user1,email: user1@test.com,name: User1}" ;
        JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
    }

    @Test
    void addUser() throws Exception {
        UserBean user = new UserBean(1, "user1", "user1@test.com", "User1");
        Mockito.when(userService.addUser(Mockito.any())).thenReturn(user);
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/library/users")
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .content("{\"username\":\"user1\", \"email\": \"user1@test.com\",\"name\": \"User1\"}")
                .contentType(MediaType.APPLICATION_JSON_VALUE);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        Assertions.assertEquals(HttpStatus.CREATED.value(), response.getStatus());
        String expected = "{id: 1,username: user1,email: user1@test.com,name: User1}";
        JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);

    }

    @Test
    void deleteUserByUsername() throws Exception {
        UserBean user = new UserBean(1, "user1", "user1@test.com", "User1");
        Mockito.when(userService.deleteUserByUsername(Mockito.anyString())).thenReturn(user);
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .delete("/library/users/user1")
                .accept(MediaType.APPLICATION_JSON_VALUE);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        Assertions.assertEquals(HttpStatus.OK.value(), response.getStatus());
        String expected = "{id: 1,username: user1,email: user1@test.com,name: User1}";
        JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);

    }

    @Test
    void updateUserByUsername() throws Exception {
        UserBean user = new UserBean(1, "user1", "user1@test.com", "User1");
        Mockito.when(userService.updateUserByUsername(Mockito.any(), Mockito.anyString())).thenReturn(user);
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .put("/library/users/user1")
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .content("{\"username\":\"user1\", \"email\": \"user1@test.com\",\"name\": \"User1\"}")
                .contentType(MediaType.APPLICATION_JSON_VALUE);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        Assertions.assertEquals(HttpStatus.OK.value(), response.getStatus());
        String expected = "{id: 1,username: user1,email: user1@test.com,name: User1}";
        JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
    }

    @Test
    void issueBookToUser() throws Exception {
        Library library = new Library(1, "New", 1);
        Mockito.when(libraryService.save(Mockito.any())).thenReturn(library);
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/library/users/user1/books/1")
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .content("{\"username\":\"New\", \"bookId\": 1}")
                .contentType(MediaType.APPLICATION_JSON_VALUE);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        Assertions.assertEquals(HttpStatus.CREATED.value(), response.getStatus());
        String expected = "{id: 1,username: New,bookId: 1}";
        JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
    }

    @Test
    void deleteBookForUser() throws Exception {
        Library library = new Library(1, "New", 1);
        Mockito.when(libraryService.deleteByUsernameAndBookId(Mockito.anyString(), Mockito.anyInt())).thenReturn(library);
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .delete("/library/users/user1/books/1")
                .accept(MediaType.APPLICATION_JSON_VALUE);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        Assertions.assertEquals(HttpStatus.OK.value(), response.getStatus());
        String expected = "{id: 1,username: New,bookId: 1}";
        JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
    }
}
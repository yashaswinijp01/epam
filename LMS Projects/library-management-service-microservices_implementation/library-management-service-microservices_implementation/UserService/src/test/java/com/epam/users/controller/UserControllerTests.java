package com.epam.users.controller;

import com.epam.users.entity.User;
import com.epam.users.service.UserService;
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
@WebMvcTest(UserController.class)
class UserControllerTests {
    @MockBean
    UserService userService;

    @Autowired
    MockMvc mockMvc;

    @Test
    void getAllUsersTest() throws Exception {
        User user1 = new User(1, "user1", "Use1@test.com", "User1");
        User user2 = new User(2, "user2", "Use2@test.com", "User2");
        User user3 = new User(3, "user3", "Use3@test.com", "User3");
        List<User> users = Arrays.asList(user1, user2, user3);
        Mockito.when(userService.findAll()).thenReturn(users);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/users")
                .accept(MediaType.APPLICATION_JSON_VALUE);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        String expected = "[" +
                "    {" +
                "        id: 1," +
                "        username: user1," +
                "        email: Use1@test.com," +
                "        name: User1" +
                "    }," +
                "    {" +
                "        id: 2," +
                "        username: user2," +
                "        email: Use2@test.com," +
                "        name: User2" +
                "    }," +
                "    {" +
                "        id: 3," +
                "        username: user3," +
                "        email: Use3@test.com," +
                "        name: User3" +
                "    }]" ;
        JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
    }

    @Test
    void getBookByUsernameWhenUserIsAvailableTest() throws Exception {
        User user = new User(1, "user1", "user1@test.com", "User1");
        Mockito.when(userService.findByUsername(Mockito.anyString())).thenReturn(user);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/users/user1")
                .accept(MediaType.APPLICATION_JSON_VALUE);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        Assertions.assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());
        String expected = "{id: 1,username: user1,email: user1@test.com,name: User1}" ;
        JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
    }

    @Test
    void getUserByUsernameWhenUserIsNotAvailableTest() throws Exception {
        Mockito.when(userService.findByUsername(Mockito.anyString())).thenReturn(null);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/users/user1")
                .accept(MediaType.APPLICATION_JSON_VALUE);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        Assertions.assertEquals(HttpStatus.BAD_REQUEST.value(), result.getResponse().getStatus());
    }

    @Test
    void addUser() throws Exception {
        User user = new User(1, "user1", "user1@test.com", "User1");
        Mockito.when(userService.save(Mockito.any())).thenReturn(user);
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/users")
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
    void updateUserByUsernameWhenUserIsAvailable() throws Exception {
        User user = new User(1, "user1", "user1@test.com", "User1");
        Mockito.when(userService.updateUserByUsername(Mockito.anyString(), Mockito.any())).thenReturn(user);
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .put("/users/user1")
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
    void updateUserByUsernameWhenUserIsNotAvailable() throws Exception {
        Mockito.when(userService.updateUserByUsername(Mockito.anyString(), Mockito.any())).thenReturn(null);
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .put("/users/user1")
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .content("{\"username\":\"user1\", \"email\": \"user1@test.com\",\"name\": \"User1\"}")
                .contentType(MediaType.APPLICATION_JSON_VALUE);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        Assertions.assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());
    }

    @Test
    void deleteUserByUsernameWhenUserIsAvailable() throws Exception {
        User user = new User(1, "user1", "user1@test.com", "User1");
        Mockito.when(userService.deleteByUsername(Mockito.anyString())).thenReturn(user);
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .delete("/users/user1")
                .accept(MediaType.APPLICATION_JSON_VALUE);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        Assertions.assertEquals(HttpStatus.OK.value(), response.getStatus());
        String expected = "{id: 1,username: user1,email: user1@test.com,name: User1}";
        JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
    }

    @Test
    void deleteUserByUsernameWhenUserIsNotAvailable() throws Exception {
        Mockito.when(userService.deleteByUsername(Mockito.anyString())).thenReturn(null);
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .delete("/users/user1")
                .accept(MediaType.APPLICATION_JSON_VALUE);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        Assertions.assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());
    }


}


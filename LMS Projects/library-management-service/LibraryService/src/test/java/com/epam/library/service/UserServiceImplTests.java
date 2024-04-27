package com.epam.library.service;

import com.epam.library.bean.UserBean;
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

@ExtendWith(MockitoExtension.class)
class UserServiceImplTests {
    @InjectMocks
    UserServiceImpl userService;

    @Mock
    RestTemplate restTemplate;

    @Test
    void getUsers() {
        UserBean user = new UserBean(1, "user", "user@test.com", "user");
        List<UserBean> users = List.of(user);

        ResponseEntity<List<UserBean>> serviceResponse =
                new ResponseEntity<>(users, HttpStatus.OK);

        Mockito.when(restTemplate.exchange(
                        ArgumentMatchers.anyString(),
                        ArgumentMatchers.any(HttpMethod.class),
                        ArgumentMatchers.any(),
                        ArgumentMatchers.<ParameterizedTypeReference<List<UserBean>>>any()))
                .thenReturn(serviceResponse);

        List<UserBean> response = userService.getUsers();
        Assertions.assertNotNull(response);
    }

    @Test
    void getUserByUsername() {
        UserBean user = new UserBean(1, "user", "user@test.com", "user");

        ResponseEntity<UserBean> serviceResponse =
                new ResponseEntity<>(user, HttpStatus.OK);

        Mockito.when(restTemplate.exchange(
                        ArgumentMatchers.anyString(),
                        ArgumentMatchers.any(HttpMethod.class),
                        ArgumentMatchers.any(),
                        ArgumentMatchers.<Class<UserBean>>any()))
                .thenReturn(serviceResponse);

        UserBean response = userService.getUserByUsername("");
        Assertions.assertNotNull(response);
    }

    @Test
    void addUser() {
        UserBean user = new UserBean(1, "user", "user@test.com", "user");

        ResponseEntity<UserBean> serviceResponse =
                new ResponseEntity<>(user, HttpStatus.OK);

        Mockito.when(restTemplate.exchange(
                        ArgumentMatchers.anyString(),
                        ArgumentMatchers.any(HttpMethod.class),
                        ArgumentMatchers.any(),
                        ArgumentMatchers.<Class<UserBean>>any()))
                .thenReturn(serviceResponse);

        UserBean response = userService.addUser(new UserBean());
        Assertions.assertNotNull(response);
    }

    @Test
    void deleteUserByUsername() {
        UserBean user = new UserBean(1, "user", "user@test.com", "user");

        ResponseEntity<UserBean> serviceResponse =
                new ResponseEntity<>(user, HttpStatus.OK);

        Mockito.when(restTemplate.exchange(
                        ArgumentMatchers.anyString(),
                        ArgumentMatchers.any(HttpMethod.class),
                        ArgumentMatchers.any(),
                        ArgumentMatchers.<Class<UserBean>>any()))
                .thenReturn(serviceResponse);

        UserBean response = userService.deleteUserByUsername("");
        Assertions.assertNotNull(response);
    }

    @Test
    void updateUserByUsername() {
        UserBean user = new UserBean(1, "user", "user@test.com", "user");

        ResponseEntity<UserBean> serviceResponse =
                new ResponseEntity<>(user, HttpStatus.OK);

        Mockito.when(restTemplate.exchange(
                        ArgumentMatchers.anyString(),
                        ArgumentMatchers.any(HttpMethod.class),
                        ArgumentMatchers.any(),
                        ArgumentMatchers.<Class<UserBean>>any()))
                .thenReturn(serviceResponse);

        UserBean response = userService.updateUserByUsername(new UserBean(), "");
        Assertions.assertNotNull(response);
    }

    @Test
    void issueBookToUser() {
        UserBean user = new UserBean(1, "user", "user@test.com", "user");

        ResponseEntity<UserBean> serviceResponse =
                new ResponseEntity<>(user, HttpStatus.OK);

        Mockito.when(restTemplate.exchange(
                        ArgumentMatchers.anyString(),
                        ArgumentMatchers.any(HttpMethod.class),
                        ArgumentMatchers.any(),
                        ArgumentMatchers.<Class<UserBean>>any()))
                .thenReturn(serviceResponse);

        UserBean response = userService.issueBookToUser("", 1);
        Assertions.assertNotNull(response);
    }
}
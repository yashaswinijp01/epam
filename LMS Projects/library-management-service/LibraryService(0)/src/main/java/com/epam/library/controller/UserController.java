package com.epam.library.controller;

import com.epam.library.bean.UserBean;
import com.epam.library.entity.Library;
import com.epam.library.exception.RecordNotFoundException;
import com.epam.library.exception.UserNotFoundException;
import com.epam.library.service.LibraryService;
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
public class UserController {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    LibraryService libraryService;

    private static final String USER_URI = "http://localhost:9099/UsersPortal/";

    @GetMapping("/library/users")
    public ResponseEntity<List<UserBean>> getUsers() {
        ResponseEntity<List> responseEntity = restTemplate.exchange(USER_URI + "users", HttpMethod.GET, null, List.class);
        List<LinkedHashMap<String, Object>> userList = responseEntity.getBody();
        ObjectMapper objectMapper = new ObjectMapper();
        List<UserBean> userBeans = new ArrayList<>();
        if (userList != null) {
            for (LinkedHashMap<String, Object> map: userList) {
                UserBean userBean = objectMapper.convertValue(map, UserBean.class);
                userBeans.add(userBean);
            }
        }
        return new ResponseEntity<>(userBeans, HttpStatus.OK);
    }

    @GetMapping("/library/users/{username}")
    public ResponseEntity<UserBean> getUserById(@PathVariable("username") String username) {
        restTemplate.setErrorHandler(new ResponseErrorHandler() {
            @Override
            public boolean hasError(ClientHttpResponse response) throws IOException {
                return response.getStatusCode() == HttpStatus.BAD_REQUEST;
            }

            @Override
            public void handleError(ClientHttpResponse response) throws IOException {
                throw new UserNotFoundException("User not found");
            }
        });
        ResponseEntity<UserBean> responseEntity = restTemplate.exchange(USER_URI + "users/" + username, HttpMethod.GET, null, UserBean.class);
        UserBean bean = responseEntity.getBody();
        return new ResponseEntity<>(bean, HttpStatus.OK);
    }

    @PostMapping("/library/users")
    public ResponseEntity<UserBean> addUser(@RequestBody UserBean userBean) {
        HttpEntity<UserBean> httpEntity = new HttpEntity<>(userBean);
        ResponseEntity<UserBean> responseEntity = restTemplate.exchange(USER_URI + "users", HttpMethod.POST, httpEntity, UserBean.class);
        UserBean bean = responseEntity.getBody();
        return new ResponseEntity<>(bean, HttpStatus.CREATED);
    }

    @DeleteMapping("/library/users/{username}")
    public ResponseEntity<UserBean> deleteUserByUsername(@PathVariable("username") String username) {
        restTemplate.setErrorHandler(new ResponseErrorHandler() {
            @Override
            public boolean hasError(ClientHttpResponse response) throws IOException {
                return response.getStatusCode() == HttpStatus.BAD_REQUEST;
            }

            @Override
            public void handleError(ClientHttpResponse response) throws IOException {
                throw new UserNotFoundException("User not found");
            }
        });
        ResponseEntity<UserBean> responseEntity = restTemplate.exchange(USER_URI + "users/" + username, HttpMethod.DELETE, null, UserBean.class);
        UserBean userBean = responseEntity.getBody();
        return new ResponseEntity<>(userBean, HttpStatus.OK);
    }

    @PutMapping("/library/users/{username}")
    public ResponseEntity<UserBean> updateUserByUsername(@RequestBody UserBean userBean, @PathVariable("username") String username) {
        restTemplate.setErrorHandler(new ResponseErrorHandler() {
            @Override
            public boolean hasError(ClientHttpResponse response) throws IOException {
                return response.getStatusCode() == HttpStatus.BAD_REQUEST;
            }

            @Override
            public void handleError(ClientHttpResponse response) throws IOException {
                throw new UserNotFoundException("User not found");
            }
        });
        HttpEntity<UserBean> httpEntity = new HttpEntity<>(userBean);
        ResponseEntity<UserBean> responseEntity = restTemplate.exchange(USER_URI + "users/" + username, HttpMethod.PUT, httpEntity, UserBean.class);
        UserBean bean = responseEntity.getBody();
        return new ResponseEntity<>(bean, HttpStatus.OK);
    }

    @PostMapping("/library/users/{username}/books/{bookId}")
    public ResponseEntity<Library> issueBookToUser(@PathVariable("username") String username, @PathVariable("bookId") int id) {
        Library library = new Library(username, id);
        restTemplate.setErrorHandler(new ResponseErrorHandler() {
            @Override
            public boolean hasError(ClientHttpResponse response) throws IOException {
                return response.getStatusCode() == HttpStatus.BAD_REQUEST;
            }

            @Override
            public void handleError(ClientHttpResponse response) throws IOException {
                throw new UserNotFoundException("User not found");
            }
        });
        ResponseEntity<UserBean> responseEntity = restTemplate.exchange(USER_URI + "users/" + username, HttpMethod.GET, null, UserBean.class);
        UserBean bean = responseEntity.getBody();
        return new ResponseEntity<>(libraryService.save(library), HttpStatus.CREATED);
    }

    @DeleteMapping("/library/users/{username}/books/{bookId}")
    public ResponseEntity<Library> deleteBookForUser(@PathVariable("username") String username, @PathVariable("bookId") int id) {
        Library library = libraryService.deleteByUsernameAndBookId(username, id);
        if (library == null)
            throw new RecordNotFoundException("No record found");
        return new ResponseEntity<>(library, HttpStatus.OK);
    }
}

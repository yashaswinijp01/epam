package com.epam.library.service;

import com.epam.library.bean.BookBean;
import com.epam.library.bean.UserBean;
import com.epam.library.exception.UserNotFoundException;
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
public class UserServiceImpl implements UserService{

    @Autowired
    RestTemplate restTemplate;

    private static final String USER_URI = "http://localhost:9402/UsersPortal/";

    @Override
    public List<UserBean> getUsers() {
        ResponseEntity<List<UserBean>> responseEntity =
                restTemplate.exchange(USER_URI + "users", HttpMethod.GET, null, new ParameterizedTypeReference<List<UserBean>>() {});
        List<UserBean> userBeans=new ArrayList<>();
        for(UserBean bean:responseEntity.getBody()){
                    userBeans.add(bean);
                }
        return userBeans;
    }

    @Override
    public UserBean getUserByUsername(String username) {
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
        return bean;
    }

    @Override
    public UserBean addUser(UserBean userBean) {
        HttpEntity<UserBean> httpEntity = new HttpEntity<>(userBean);
        ResponseEntity<UserBean> responseEntity = restTemplate.exchange(USER_URI + "users", HttpMethod.POST, httpEntity, UserBean.class);
        UserBean bean = responseEntity.getBody();
        return bean;
    }

    @Override
    public UserBean deleteUserByUsername(String username) {
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
        return userBean;
    }

    @Override
    public UserBean updateUserByUsername(UserBean userBean, String username) {
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
        return bean;
    }

    @Override
    public UserBean issueBookToUser(String username, int id) {
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
        return bean;
    }
}

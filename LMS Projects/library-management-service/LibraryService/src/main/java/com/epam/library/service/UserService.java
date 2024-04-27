package com.epam.library.service;

import com.epam.library.bean.UserBean;

import java.util.List;

public interface UserService {
    List<UserBean> getUsers();
    UserBean getUserByUsername(String username);
    UserBean addUser(UserBean userBean);
    UserBean deleteUserByUsername(String username);
    UserBean updateUserByUsername(UserBean userBean, String username);
    UserBean issueBookToUser(String username, int id);
}

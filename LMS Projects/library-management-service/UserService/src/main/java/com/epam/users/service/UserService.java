package com.epam.users.service;

import com.epam.users.bean.UserBean;
import com.epam.users.entity.User;

import java.util.List;

public interface UserService {
    List<User> findAll();
    User findByUsername(String username);
    User save(UserBean userBean);
    User updateUserByUsername(String username, UserBean userBean);
    User deleteByUsername(String username);
}

package com.epam.users.service;

import com.epam.users.bean.UserBean;
import com.epam.users.dao.UserDaoWrapper;
import com.epam.users.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    UserDaoWrapper userDaoWrapper;

    @Override
    public List<User> findAll() {
        return userDaoWrapper.findAll();
    }

    @Override
    public User findByUsername(String username) {
        return userDaoWrapper.findByUsername(username);
    }

    @Override
    public User save(UserBean userBean) {
        return userDaoWrapper.save(userBean);
    }

    @Override
    public User updateUserByUsername(String username, UserBean userBean) {
        return userDaoWrapper.updateByUsername(username, userBean);
    }

    @Override
    public User deleteByUsername(String username) {
        return userDaoWrapper.deleteByUsername(username);
    }
}

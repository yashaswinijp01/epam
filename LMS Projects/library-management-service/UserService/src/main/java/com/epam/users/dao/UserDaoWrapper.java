package com.epam.users.dao;

import com.epam.users.bean.UserBean;
import com.epam.users.entity.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDaoWrapper {
    @Autowired
    UserDao userDao;

    public User save(UserBean userBean) {
        User user = convertBeanToEntity(userBean);
        return userDao.save(user);
    }

    public List<User> findAll() {
        return userDao.findAll();
    }

    public User findByUsername(String username) {
        return userDao.findByUsername(username);
    }

    public User updateByUsername(String username, UserBean userBean){
        User user = findByUsername(username);
        if (user == null)
            return null;
        user.setUsername(userBean.getUsername());
        user.setEmail(userBean.getEmail());
        user.setName(userBean.getName());
        return userDao.save(user);
    }

    public User deleteByUsername(String username) {
        User user = findByUsername(username);
        if (user == null)
            return null;
        userDao.delete(user);
        return user;
    }

    private User convertBeanToEntity(UserBean userBean) {
        User user = new User();
        BeanUtils.copyProperties(userBean, user);
        return user;
    }
}

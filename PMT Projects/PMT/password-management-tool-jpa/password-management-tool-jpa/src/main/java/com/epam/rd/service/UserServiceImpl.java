package com.epam.rd.service;

import com.epam.rd.bean.UserBean;
import com.epam.rd.bean.UserDetailsBean;
import com.epam.rd.dao.interfaces.UserDaoWrapper;
import com.epam.rd.exception.DuplicateUserException;
import com.epam.rd.service.interfaces.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service("userService")
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDaoWrapper userDaoWrapper;
    private static final Logger logger
            = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public void saveUser(UserBean userBean) throws DuplicateUserException {
        logger.info("saving user bean");
        userBean.setCreatedAt(new Date());
        userBean.setLastModifiedAt(new Date());
        userDaoWrapper.saveUser(userBean);
        logger.info("User Bean saved Successfully");
    }

    @Override
    public UserBean getUserByUserName(String userName) {
        logger.info("getting user by name");
        return userDaoWrapper.getUserByUserName(userName);
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        logger.info("loading user by name");
        UserBean userBean = getUserByUserName(userName);
        return new UserDetailsBean(userBean);
    }
}

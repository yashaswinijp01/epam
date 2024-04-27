package com.epam.rd.service.interfaces;

import com.epam.rd.bean.UserBean;
import com.epam.rd.exception.DuplicateUserException;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    void saveUser(UserBean userBean) throws DuplicateUserException;
    UserBean getUserByUserName(String userName);
}

package com.epam.rd.dao.interfaces;

import com.epam.rd.bean.UserBean;
import com.epam.rd.exception.DuplicateUserException;

public interface UserDaoWrapper {
    void saveUser(UserBean userBean) throws DuplicateUserException;
    UserBean getUserByUserName(String userName) ;
}

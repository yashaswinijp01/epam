package com.epam.rd.dao;

import com.epam.rd.bean.UserBean;
import com.epam.rd.dao.interfaces.UserDao;
import com.epam.rd.exception.DuplicateUserException;
import com.epam.rd.entity.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserDaoWrapperImplTest {

    @InjectMocks
    UserDaoWrapperImpl userDaoWrapper;

    @Mock
    UserDao userDao;
    @Mock
    ModelMapper modelMapper;
    @Mock
    User user;
    UserBean userBean;
    @Before
    public void setUp(){
        userBean = new UserBean("","","");
        user = new User("" ,"","");
        when(modelMapper.map(userBean, User.class)).thenReturn(user);
        when(modelMapper.map(user , UserBean.class)).thenReturn(userBean);
    }

    @Test
    @DisplayName("UserDaoWrapper should return user by user name")
    public void userDaoWrapperShouldReturnUserByUserName(){
        when(userDao.findByUserName(anyString())).thenReturn(List.of(user));
        Assertions.assertDoesNotThrow(()-> userDaoWrapper.getUserByUserName(""));
    }

    @Test
    @DisplayName("UserDaoWrapper should return user by user name")
    public void userDaoWrapperShouldReturnUserByUserName1(){
        when(userDao.findByUserName(anyString())).thenReturn(List.of());
        Assertions.assertThrows(UsernameNotFoundException.class ,()-> userDaoWrapper.getUserByUserName(""));
    }

    @Test
    @DisplayName("UserDaoWrapper should save unique user")
    public void userDaoWrapperShouldSaveUniqueUser(){
        when(userDao.countByUserName(anyString())).thenReturn(0);
        Assertions.assertDoesNotThrow(()-> userDaoWrapper.saveUser(userBean));
    }

    @Test
    @DisplayName("UserDaoWrapper should save should throw exception for duplicate user")
    public void userDaoWrapperShouldThrowExceptionForDuplicateUser(){
        when(userDao.countByUserName(anyString())).thenReturn(1);
        Assertions.assertThrows(DuplicateUserException.class ,()-> userDaoWrapper.saveUser(userBean));
    }

}

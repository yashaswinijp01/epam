package com.epam.rd.service;

import com.epam.rd.bean.UserBean;
import com.epam.rd.dao.interfaces.UserDaoWrapper;
import com.epam.rd.exception.DuplicateUserException;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {

    @InjectMocks
    UserServiceImpl userService;

    @Mock
    UserDaoWrapper userDaoWrapper;

    @Test
    @DisplayName("save User Should Check For DuplicateUser")
    public void saveUserShouldCheckForDuplicateUser() throws DuplicateUserException {
        UserBean userBean = new UserBean();
        doThrow(DuplicateUserException.class).when(userDaoWrapper).saveUser(any());
        Assertions.assertThrows(DuplicateUserException.class , () -> userService.saveUser(userBean));
    }

    @Test
    @DisplayName("UserDaoWrapper save user should save unique user")
    public void saveUserShouldSaveUniqueUser() {
        UserBean userBean = new UserBean();
        Assertions.assertDoesNotThrow(() -> userService.saveUser(userBean));
    }

    @Test
    @DisplayName("getUserByUserName should throw exception if user not found")
    public void getUserByUserNameShouldThrowExceptionIfUserNotFound()    {
        doThrow(UsernameNotFoundException.class).when(userDaoWrapper).getUserByUserName(any());
        Assertions.assertThrows(UsernameNotFoundException.class , () -> userService.getUserByUserName("user"));
    }
    @Test
    @DisplayName("User Service should return user by user name")
    public void getUserByUserNameShouldUserByUserName()    {
        Assertions.assertDoesNotThrow(() -> userService.getUserByUserName("user"));
    }
    @Test
    @DisplayName("User Service should return UserDetails by user name")
    public void userServiceShouldReturnUserDetails()    {
        Assertions.assertDoesNotThrow(() -> userService.loadUserByUsername("user"));
    }

    @Test
    @DisplayName("load should throw exception if user not found")
    public void loadUserByUserNameShouldThrowExceptionIfUserNotFound()    {
        doThrow(UsernameNotFoundException.class).when(userDaoWrapper).getUserByUserName(any());
        Assertions.assertThrows(UsernameNotFoundException.class , () -> userService.loadUserByUsername("user"));
    }

}

package com.epam.users.service;

import com.epam.users.bean.UserBean;
import com.epam.users.dao.UserDaoWrapper;
import com.epam.users.entity.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith({MockitoExtension.class})
class UserServiceImplTest {

    @InjectMocks
    UserServiceImpl userService;

    @Mock
    UserDaoWrapper userDaoWrapper;

    @Test
    void findAll() {
        User user1 = new User(1, "user1", "Use1@test.com", "User1");
        User user2 = new User(2, "user2", "Use2@test.com", "User2");
        User user3 = new User(3, "user3", "Use3@test.com", "User3");
        List<User> users = Arrays.asList(user1, user2, user3);
        Mockito.when(userDaoWrapper.findAll()).thenReturn(users);

        List<User> result = userService.findAll();
        Assertions.assertEquals(3, result.size());
        Mockito.verify(userDaoWrapper, Mockito.times(1)).findAll();

    }

    @Test
    void findByUsername() {
        User user = new User(1, "user", "Use@test.com", "User");
        Mockito.when(userDaoWrapper.findByUsername(Mockito.anyString())).thenReturn(user);
        User foundUser = userService.findByUsername("user");
        Assertions.assertEquals(user.getUsername(), foundUser.getUsername());
        Assertions.assertEquals(user.getEmail(),foundUser.getEmail());
        Assertions.assertEquals(user.getName(), foundUser.getName());
    }

    @Test
    void findByUsernameWhenUserNotFound() {
        Mockito.when(userDaoWrapper.findByUsername(Mockito.anyString())).thenReturn(null);
        User foundUser = userService.findByUsername("user");
        Assertions.assertNull(foundUser);
    }

    @Test
    void save() {
        User user = new User(1, "user", "Use@test.com", "User");
        Mockito.when(userDaoWrapper.save(Mockito.any())).thenReturn(user);
        User savedUser = userService.save(new UserBean());
        Assertions.assertEquals(user.getUsername(), savedUser.getUsername());
        Assertions.assertEquals(user.getEmail(), savedUser.getEmail());
        Assertions.assertEquals(user.getName(), savedUser.getName());
    }

    @Test
    void updateUserByUsername() {
        User user = new User(1, "user", "Use@test.com", "User");
        Mockito.when(userDaoWrapper.updateByUsername(Mockito.anyString(), Mockito.any())).thenReturn(user);
        User updatedUser = userService.updateUserByUsername("", new UserBean());
        assertEquals(user.getUsername(), updatedUser.getUsername());
        assertEquals(user.getEmail(), updatedUser.getEmail());
        assertEquals(user.getName(), updatedUser.getName());
    }

    @Test
    void updateUserByUsernameWhenUserNotFound() {
        Mockito.when(userDaoWrapper.updateByUsername(Mockito.anyString(), Mockito.any())).thenReturn(null);
        User updatedUser = userService.updateUserByUsername("", new UserBean());
        Assertions.assertNull(updatedUser);
    }

    @Test
    void deleteByUsername() {
        User user = new User(1, "user", "Use@test.com", "User");
        Mockito.when(userDaoWrapper.deleteByUsername(Mockito.anyString())).thenReturn(user);
        User deletedUser = userService.deleteByUsername("");
        assertEquals(user.getUsername(), deletedUser.getUsername());
        assertEquals(user.getEmail(), deletedUser.getEmail());
        assertEquals(user.getName(), deletedUser.getName());
    }

    @Test
    void deleteByUsernameWhenUserNotFound() {
        Mockito.when(userDaoWrapper.deleteByUsername(Mockito.anyString())).thenReturn(null);
        User deletedUser = userService.deleteByUsername("");
        Assertions.assertNull(deletedUser);
    }
}
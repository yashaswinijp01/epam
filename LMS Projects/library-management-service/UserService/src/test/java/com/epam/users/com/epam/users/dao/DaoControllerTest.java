package com.epam.users.com.epam.users.dao;
import com.epam.users.bean.UserBean;
import com.epam.users.dao.UserDao;
import com.epam.users.dao.UserDaoWrapper;
import com.epam.users.entity.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

@ExtendWith(MockitoExtension.class)
public class DaoControllerTest {

    @InjectMocks
    UserDaoWrapper userDaoWrapper;

    @Mock
    UserDao userDao;

    @Test
    void save() {
        User user = new User("user", "email@test.com", "name");
        Mockito.when(userDao.save(Mockito.any())).thenReturn(user);
        User user1 = userDaoWrapper.save(new UserBean());
        Assertions.assertEquals(user.getUsername(), user1.getUsername());
        Assertions.assertEquals(user.getEmail(), user1.getEmail());
        Assertions.assertEquals(user.getName(), user1.getName());
    }

    @Test
    void findAll() {
        User user = new User("user", "email@test.com", "name");
        List<User> users = List.of(user);
        Mockito.when(userDao.findAll()).thenReturn(users);
        List<User> userList = userDaoWrapper.findAll();
        Assertions.assertEquals(1, userList.size());
    }

    @Test
    void findByUsername() {
        User user = new User("user", "email@test.com", "name");
        Mockito.when(userDao.findByUsername(Mockito.anyString())).thenReturn(user);
        User user1 = userDaoWrapper.findByUsername("");
        Assertions.assertEquals(user.getName(), user1.getName());
        Assertions.assertEquals(user.getUsername(), user1.getUsername());
        Assertions.assertEquals(user.getEmail(), user1.getEmail());
    }

    @Test
    void deleteByUsername() {
        User user = new User("user", "email@test.com", "name");
        Mockito.when(userDao.findByUsername(Mockito.anyString())).thenReturn(user);
        User user1 = userDaoWrapper.deleteByUsername("");
        Assertions.assertEquals(user.getUsername(), user1.getUsername());
        Assertions.assertEquals(user.getEmail(), user1.getEmail());
        Assertions.assertEquals(user.getName(), user1.getName());
    }

    @Test
    void updateByUsername() {
        User user = new User("user", "email@test.com", "name");
        Mockito.when(userDao.findByUsername(Mockito.anyString())).thenReturn(user);
        Mockito.when(userDao.save(Mockito.any())).thenReturn(user);
        User user1 = userDaoWrapper.updateByUsername("", new UserBean());
        Assertions.assertEquals(user.getUsername(), user1.getUsername());
        Assertions.assertEquals(user.getEmail(), user1.getEmail());
        Assertions.assertEquals(user.getName(), user1.getName());
    }


}

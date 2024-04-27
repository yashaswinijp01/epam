package com.epam.users.controller;

import com.epam.users.bean.UserBean;
import com.epam.users.entity.User;
import com.epam.users.exception.UserNotFoundException;
import com.epam.users.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<User>> findUsers() {
        return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/users/{username}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> findUserByUsername(@PathVariable("username") String username) {
        User user = userService.findByUsername(username);
        if (user == null)
            throw new UserNotFoundException("User Not Found");
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping(value = "/users", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> addUser(@RequestBody @Valid UserBean userBean) {
        return new ResponseEntity<>(userService.save(userBean), HttpStatus.CREATED);
    }

    @PutMapping(value = "/users/{username}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> updateUserByUsername(@PathVariable("username") String username, @RequestBody @Valid UserBean userBean) {
        User user = userService.updateUserByUsername(username, userBean);
        if (user == null)
            throw new UserNotFoundException("User Not Found");
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @DeleteMapping(value = "/users/{username}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> deleteUserByUsername(@PathVariable("username") String username) {
        User user = userService.deleteByUsername(username);
        if (user == null)
            throw new UserNotFoundException("User Not Found");
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}

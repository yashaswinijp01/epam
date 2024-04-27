package com.epam.library.controller;

import com.epam.library.bean.UserBean;
import com.epam.library.entity.Library;
import com.epam.library.exception.RecordNotFoundException;
import com.epam.library.service.LibraryService;
import com.epam.library.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    LibraryService libraryService;

    @GetMapping("/library/users")
    public ResponseEntity<List<UserBean>> getUsers() {
        List<UserBean> userBeans = userService.getUsers();
        return new ResponseEntity<>(userBeans, HttpStatus.OK);
    }

    @GetMapping("/library/users/{username}")
    public ResponseEntity<UserBean> getUserByName(@PathVariable("username") String username) {
        UserBean bean = userService.getUserByUsername(username);
        return new ResponseEntity<>(bean, HttpStatus.OK);
    }

    @PostMapping("/library/users")
    public ResponseEntity<UserBean> addUser(@RequestBody UserBean userBean) {
        UserBean bean = userService.addUser(userBean);
        return new ResponseEntity<>(bean, HttpStatus.CREATED);
    }

    @DeleteMapping("/library/users/{username}")
    public ResponseEntity<UserBean> deleteUserByUsername(@PathVariable("username") String username) {
        UserBean userBean = userService.deleteUserByUsername(username);
        return new ResponseEntity<>(userBean, HttpStatus.OK);
    }

    @PutMapping("/library/users/{username}")
    public ResponseEntity<UserBean> updateUserByUsername(@RequestBody UserBean userBean, @PathVariable("username") String username) {
        UserBean bean = userService.updateUserByUsername(userBean, username);
        return new ResponseEntity<>(bean, HttpStatus.OK);
    }

    @PostMapping("/library/users/{username}/books/{bookId}")
    public ResponseEntity<Library> issueBookToUser(@PathVariable("username") String username, @PathVariable("bookId") int id) {
        Library library = new Library(username, id);
        userService.issueBookToUser(username, id);
        return new ResponseEntity<>(libraryService.save(library), HttpStatus.CREATED);
    }

    @DeleteMapping("/library/users/{username}/books/{bookId}")
    public ResponseEntity<Library> deleteBookForUser(@PathVariable("username") String username, @PathVariable("bookId") int id) {
        Library library = libraryService.deleteByUsernameAndBookId(username, id);
        if (library == null)
            throw new RecordNotFoundException("No record found");
        return new ResponseEntity<>(library, HttpStatus.OK);
    }
}

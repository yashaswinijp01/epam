package com.example.pmt_tool.restcontrollers;
import com.example.pmt_tool.dto.UserDto;
import com.example.pmt_tool.entities.User;
import com.example.pmt_tool.repositories.UserRepo;
import com.example.pmt_tool.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class UserRestController {
    @Autowired
    UserRepo userRepo;
    @Autowired
    UserService userService;

    @GetMapping("/users")
    public ResponseEntity<List<User>> getUsers() {
         List<User> users=userRepo.findAll();
         return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/users/{userName}")
    public ResponseEntity<UserDto> getUserByUserName(@PathVariable("userName") String userName) {
        UserDto userDto= userService.findByUserName(userName);
        return new ResponseEntity<>(userDto,HttpStatus.OK);
    }
    @PostMapping("/users")
    public ResponseEntity<String> saveUser(@Valid @RequestBody UserDto userDto, Errors errors){
        if(errors.hasErrors()){
            return new ResponseEntity<>(errors.getAllErrors()+" ",HttpStatus.BAD_REQUEST);
        }
        else{
            userService.save(userDto);
            return new ResponseEntity<>("Added user",HttpStatus.OK);
        }
    }
}

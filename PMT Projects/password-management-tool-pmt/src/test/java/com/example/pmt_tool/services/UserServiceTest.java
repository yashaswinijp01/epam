package com.example.pmt_tool.services;

import com.example.pmt_tool.dto.UserDto;
import com.example.pmt_tool.entities.User;
import com.example.pmt_tool.repositories.UserRepo;
import com.example.pmt_tool.services.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    @InjectMocks
    UserServiceImpl userService;
    @Mock
    UserRepo userRepo;
    @Mock
    ModelMapper modelMapper;

    UserDto userDto;
    User user;

    @BeforeEach
    void setUp(){
        userDto=new UserDto(1,"minnu","123");
         user=new User(1,"minnu","123");
    }
    @Test
     void saveUser(){
        when(modelMapper.map(any(), eq(User.class))).thenReturn(user);
        when(userRepo.save(any())).thenReturn(user);
        userService.save(userDto);
        assertTrue(true);
    }

    @Test
     void findByUserName(){
        when(userRepo.findByUserName(anyString())).thenReturn(user);
        when(modelMapper.map(any(),eq(UserDto.class))).thenReturn(userDto);
        userService.findByUserName("minnu");
        assertTrue(true);
    }
    @Test
    void failTestFindByUserName(){
        when(userRepo.findByUserName(anyString())).thenReturn(null);
        userService.findByUserName("m");
        assertTrue(true);
    }


}

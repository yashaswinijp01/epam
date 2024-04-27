package com.example.pmt_tool.restcontrollers;

import com.example.pmt_tool.dto.UserDto;
import com.example.pmt_tool.entities.User;
import com.example.pmt_tool.repositories.UserRepo;
import com.example.pmt_tool.services.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.collection.IsCollectionWithSize;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class UserRestControllerTest {
    @InjectMocks
    UserRestController userRestController;
    @Mock
    UserRepo userRepo;
    @Mock
    UserService userService;
    @Autowired
    private MockMvc mockMvc;
    @Mock
    ObjectMapper objectMapper;
    User user;
    UserDto userDto;
    @BeforeEach
    void setup(){
        mockMvc = MockMvcBuilders.standaloneSetup(userRestController).build();
        objectMapper = new ObjectMapper();
        user=new User(1,"minnu","Minu@124HS");
        userDto=new UserDto(1,"minnu","Minu@124HS");
    }
    @Test
    void testGetUsers() throws Exception {
        List<User> users=new ArrayList<>();
        users.add(user);
        when(userRepo.findAll()).thenReturn(users);
        String json = objectMapper.writeValueAsString(users);
        mockMvc.perform(get("/users")).andExpect(status().isOk()).andExpect(jsonPath("$", IsCollectionWithSize.hasSize(1)))
                .andExpect(jsonPath("$[0].userName", equalTo("minnu")));
    }

    @Test
     void testSaveUser() throws Exception {
        doNothing().when(userService).save(userDto);
        String json = objectMapper.writeValueAsString(userDto);
        mockMvc.perform(post("/users").contentType(MediaType.APPLICATION_JSON)
                .content(json).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    }

    @Test
     void failTestSaveUser() throws Exception {
        String json = objectMapper.writeValueAsString(new UserDto());
        mockMvc.perform(post("/users").contentType(MediaType.APPLICATION_JSON)
                .content(json).accept(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest());
    }

    @Test
     void testFindUserByUserName() throws Exception{
        when(userService.findByUserName(anyString())).thenReturn(userDto);
        String json = objectMapper.writeValueAsString(userDto);
        mockMvc.perform(get("/users/{username}","minnu")).andExpect(status().isOk());
    }
}

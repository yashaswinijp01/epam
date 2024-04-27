package com.example.pmt_tool.controllers;

import com.example.pmt_tool.dto.UserDto;
import com.example.pmt_tool.controller.UserController;
import com.example.pmt_tool.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {
    @InjectMocks
    UserController userController;
    @Mock
    UserService userService;
    private MockMvc mockMvc;
    UserDto userDto;

    @BeforeEach
    void setup(){
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
        userDto=new UserDto(1,"minnu","hgD@#124as");
    }

    @Test
    void testCreateUser() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/user/new"))
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
    }

    @Test
    void testRegisterUser() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/register/user"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
    @Test
    void testLogoutUser() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/user/logout"))
                .andExpect(redirectedUrl("/user/new"));
    }
    @Test
     void testValidateUser() throws Exception {
        UserDto userDto1=new UserDto(1,"minnu","hgD@#124as");
        when(userService.findByUserName(anyString())).thenReturn(userDto1);
        mockMvc.perform(MockMvcRequestBuilders.post("/user/save")
                        .flashAttr("userDto",userDto1))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
    @Test
     void testSaveRegisteredUser() throws Exception {
        doNothing().when(userService).save(any());
        mockMvc.perform(MockMvcRequestBuilders.post("/register/saveuser")
                        .flashAttr("userDto",userDto))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
    @Test
    public void failTestValidateUser() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/user/save")
                        .flashAttr("userDto",new UserDto()))
                .andExpect(redirectedUrl("/user/new"));

    }
    @Test
    void failTestSaveRegisteredUserIfNull() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/register/saveuser")
                    .flashAttr("userDto",new UserDto()))
            .andExpect(MockMvcResultMatchers.status().isOk());
    }
    @Test
    void failTestSaveRegisteredUserIfInvalidDetails() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/register/saveuser")
                        .flashAttr("userDto",new UserDto(9,"ml","mn")))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

}

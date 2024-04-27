package com.epam.rd.controller;

import com.epam.rd.restController.AuthController;
import com.epam.rd.bean.UserBean;
import com.epam.rd.service.interfaces.UserService;
import com.epam.rd.utils.JwtTokenUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(value = AuthController.class)
@WithMockUser
public class AuthControllerTest {
    @MockBean
    AuthenticationManager authenticationManager;
    @MockBean
    JwtTokenUtil jwtTokenUtil;
    @MockBean
    UserService userService;
    @MockBean
    Authentication authentication;
    @Autowired
    MockMvc mockMvc;
    UserBean userBean;



    @Before
    public void setUp(){
        userBean = new UserBean();
    }

    @Test
    @DisplayName("loginUser Should login the valid user")
    public void loginUserShouldLoginTheValidUserWithOkStatusCode() throws Exception {
        when(authenticationManager.authenticate(any())).thenReturn(authentication);
        UserBean userBean = new UserBean("","","");
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/login")
                        .content(asJsonString(userBean))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

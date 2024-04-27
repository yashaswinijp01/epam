package com.epam.rd.controller;

import com.epam.rd.bean.UserBean;
import com.epam.rd.service.interfaces.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(value = AuthController.class)
public class AuthControllerTest {
    @MockBean
    UserService userService;
    @Autowired
    MockMvc mockMvc;
    UserBean userBean;

    @Before
    public void setUp(){
        userBean = new UserBean();
    }

    @Test
    @DisplayName("loginUser Should give login page if user is not logged in")
    public void loginUserShouldGiveLoginPageIfUserIsNotLoggedIn() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/login"))
                .andExpect(view().name("login"))
                .andExpect(forwardedUrl("/WEB-INF/jsp/login.jsp"));
    }

    @Test
    @DisplayName("loginUser Should redirect to all groups if user is logged in")
    @WithMockUser
    public void loginUserShouldRedirectToAllGroupsIfUserIsLoggedIn() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/login"))
                .andExpect(view().name("redirect:/groups"));
    }

    @Test
    @DisplayName("user Should redirect to all groups if user is logged in")
    @WithMockUser
    public void userShouldRedirectToAllGroupsIfUserIsLoggedIn() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/"))
                .andExpect(view().name("redirect:/groups"));
    }

}

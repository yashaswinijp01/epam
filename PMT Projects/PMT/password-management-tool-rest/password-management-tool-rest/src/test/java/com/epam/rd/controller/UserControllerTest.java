package com.epam.rd.controller;

import com.epam.rd.restController.UserController;
import com.epam.rd.bean.UserBean;
import com.epam.rd.exception.DuplicateUserException;
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
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(value = UserController.class)
@WithMockUser
public class UserControllerTest {

    @MockBean
    JwtTokenUtil jwtTokenUtil;
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
    @DisplayName("saveUserShould Give Bad Request Status If User is invalid")
    public void saveUserShouldGiveValidationMessagesWithBadRequestCode() throws Exception {
        UserBean userBean = new UserBean("","","");
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/register")
                        .content(asJsonString(userBean))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("saveUser Should Give conflict Status If User is duplicate")
    public void saveUserShouldGiveConflictStatusCodeForDuplicateUser() throws Exception {
        UserBean userBean = new UserBean("Harish Kumar","jindalhoney","Harish@1234");
        doThrow(DuplicateUserException.class).when(userService).saveUser(any());
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/register")
                        .content(asJsonString(userBean))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isConflict());
    }

    @Test
    @DisplayName("saveUser Should Give Ok Status If User saved")
    public void saveUserShouldSaveUserWithOkStatus() throws Exception {
        UserBean userBean = new UserBean("Harish Kumar","jindalhoney","Harish@1234");
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/register")
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

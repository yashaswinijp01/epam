package com.epam.rd.controller;

import com.epam.rd.restController.AccountController;
import com.epam.rd.bean.AccountBean;
import com.epam.rd.exception.AccountDoesNotExistException;
import com.epam.rd.exception.DuplicateAccountException;
import com.epam.rd.service.interfaces.AccountService;
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
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(value = AccountController.class)
@WithMockUser
public class AccountControllerTest {
    @MockBean
    AccountService accountService;
    @MockBean
    JwtTokenUtil jwtTokenUtil;
    @MockBean
    UserService userService;
    @Autowired
    MockMvc mockMvc;
    AccountBean accountBean;



    @Before
    public void setUp(){
        accountBean = new AccountBean();
    }

    @Test
    @DisplayName("getAccountById Should Give Ok Status If Account Exist")
    public void getAccountByIdShouldGiveOkStatusIfAccountExist() throws Exception {
        when(accountService.getAccountById(anyInt())).thenReturn(accountBean);
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/accounts/account/2")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("getAccountById Should Give Not Found Status If Account not Exist")
    public void getAccountByIdShouldGiveNotFoundStatusIfAccountNotExist() throws Exception {
        doThrow(AccountDoesNotExistException.class).when(accountService).getAccountById(anyInt());
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/accounts/account/2")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("deleteAccount Should Give Ok Status If Account Exist")
    public void deleteShouldGiveOkStatusIfAccountExist() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/accounts/account/2")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("deleteAccount Should Give Not found Status If Account not Exist")
    public void deleteAccountShouldGiveNotFoundStatusIfAccountNotExist() throws Exception {
        doThrow(AccountDoesNotExistException.class).when(accountService).removeAccountById(anyInt());
        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/accounts/account/2")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("saveAccount Should Give Bad Request Status If Account is invalid")
    public void saveAccountShouldGiveValidationMessagesWithBadRequestCode() throws Exception {
        AccountBean accountBean = new AccountBean("","","","");
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/accounts/account")
                        .content(asJsonString(accountBean))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("saveAccount Should Give conflict Status If Account is duplicate")
    public void saveAccountShouldGiveConflictStatusCodeForDuplicateAccount() throws Exception {
        AccountBean accountBean = new AccountBean("jindalhoney","gmailaccount","Harish@1234","");
        doThrow(DuplicateAccountException.class).when(accountService).saveAccount(any());
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/accounts/account")
                        .content(asJsonString(accountBean))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isConflict());
    }

    @Test
    @DisplayName("saveAccount Should Give Ok Status If Account saved")
    public void saveAccountShouldSaveAccountWithOkStatus() throws Exception {
        AccountBean accountBean = new AccountBean("jindalhoney","gmailaccount","Harish@1234","");
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/accounts/account")
                        .content(asJsonString(accountBean))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("updateAccount Should Give Bad request Status If Account is invalid")
    public void updateAccountShouldGiveValidationMessagesWithBadRequestCode() throws Exception {
        AccountBean accountBean = new AccountBean("","","","");
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/accounts/account/1")
                        .content(asJsonString(accountBean))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("updateAccount Should Give conflict Status If Account is duplicate")
    public void updateAccountShouldGiveConflictStatusCodeForDuplicateAccount() throws Exception {
        AccountBean accountBean = new AccountBean("jindalhoney","gmailaccount","Harish@1234","");
        doThrow(DuplicateAccountException.class).when(accountService).updateAccountById(any() , anyInt());
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/accounts/account/1")
                        .content(asJsonString(accountBean))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isConflict());
    }

    @Test
    @DisplayName("updateAccount Should Give Ok Status If Account updated")
    public void updateAccountShouldSaveUpdatedAccountWithOkStatus() throws Exception {
        AccountBean accountBean = new AccountBean("jindalhoney","gmailaccount","Harish@1234","");
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/accounts/account/1")
                        .content(asJsonString(accountBean))
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

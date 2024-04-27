package com.example.pmt_tool.restcontrollers;

import com.example.pmt_tool.dto.AccountDTO;
import com.example.pmt_tool.dto.GroupDTO;
import com.example.pmt_tool.exceptions.AccountNotFoundException;
import com.example.pmt_tool.exceptionhandler.CustomExceptionHandler;
import com.example.pmt_tool.services.AccountService;
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
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@ExtendWith(MockitoExtension.class)
class AccountRestControllerTest {
    @InjectMocks
    AccountRestController accountRestController;
    @Mock
    AccountService accountService;
    @Autowired
    private MockMvc mockMvc;
    @Mock
    ObjectMapper objectMapper;
    AccountDTO accountDTO;

    @BeforeEach
    void setup(){
        mockMvc = MockMvcBuilders.standaloneSetup(accountRestController).setControllerAdvice(CustomExceptionHandler.class).build();
        objectMapper = new ObjectMapper();
        accountDTO=new AccountDTO(2,"chandana","swethas","dsg@#12H3","https://www.google.com",new GroupDTO(2,"general"));
    }
    @Test
    void testAddAccount() throws Exception {
        doNothing().when(accountService).saveAccount(accountDTO);
        String json = objectMapper.writeValueAsString(accountDTO);
        mockMvc.perform(post("/accounts").contentType(MediaType.APPLICATION_JSON)
                .content(json).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    }
    @Test
    void testListAccounts() throws Exception {
        List<AccountDTO> accountDTOS=new ArrayList<>();
        accountDTOS.add(accountDTO);
        when(accountService.findAll()).thenReturn(accountDTOS);
        mockMvc.perform(get("/accounts")).andExpect(status().isOk()).andExpect(jsonPath("$", IsCollectionWithSize.hasSize(1)))
                .andExpect(jsonPath("$[0].accountName", equalTo("swethas")));
    }
    @Test
    void testListAccountById() throws Exception {
        List<AccountDTO> accountDTOS=new ArrayList<>();
        accountDTOS.add(accountDTO);
        when(accountService.findAccountsByGroupId(anyInt())).thenReturn(accountDTOS);
        mockMvc.perform(get("/accounts/groups/{id}",1)).andExpect(status().isOk());
    }
    @Test
    void failTestAddAccount() throws Exception {
        String json = objectMapper.writeValueAsString(new AccountDTO());
        mockMvc.perform(post("/accounts").contentType(MediaType.APPLICATION_JSON)
                .content(json).accept(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest());
    }
    @Test
    void testDeleteAccount() throws Exception{
        doNothing().when(accountService).deleteById(anyInt());
        mockMvc.perform(delete("/accounts/{id}",1)).andExpect(status().isOk());

    }
    @Test
    void testFindAccountById() throws Exception{
        when(accountService.findById(anyInt())).thenReturn(accountDTO);
        mockMvc.perform(get("/accounts/{id}",1)).andExpect(status().isOk());
    }

    @Test
     void testDeleteAccountByGroupId() throws Exception {
        doNothing().when(accountService).deleteAccountByGroupId(anyInt());
        mockMvc.perform(delete("/accounts/group/{id}",1)).andExpect(status().isOk());

    }

    @Test
    void failTestDeleteAccountException() throws Exception{
        doThrow(new AccountNotFoundException("Account Not found exception")).when(accountService).deleteById(anyInt());
        mockMvc.perform(delete("/accounts/{id}",1)).andExpect(status().isInternalServerError());

    }
    @Test
    void failTestForFindAccountById() throws Exception {
        when(accountService.findById(anyInt())).thenThrow(new AccountNotFoundException("Account Not found exception"));
        mockMvc.perform(get("/accounts/{id}",1)).andExpect(status().isInternalServerError());
    }
}

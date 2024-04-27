package com.example.pmt_tool.controllers;

import com.example.pmt_tool.dto.AccountDTO;
import com.example.pmt_tool.dto.GroupDTO;
import com.example.pmt_tool.controller.AccountController;
import com.example.pmt_tool.services.AccountService;
import com.example.pmt_tool.services.GroupService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;

@ExtendWith(MockitoExtension.class)
class AccountControllerTest {
    @InjectMocks
    AccountController accountController;

    @Mock
    AccountService accountService;

    @Mock
    GroupService groupService;

    private MockMvc mockMvc;

    GroupDTO groupDTO;

    AccountDTO accountDTO;

    @BeforeEach
    void setup(){
        mockMvc = MockMvcBuilders.standaloneSetup(accountController).build();
        groupDTO = new GroupDTO(1,"CSE");
        accountDTO=new AccountDTO(2,"chandana","swethas","dsg@#12H3","https://www.google.com",new GroupDTO(2,"general"));
    }

    @Test
    void  testAddAccount() throws Exception {
        List<GroupDTO> data = new ArrayList<>();
        data.add(groupDTO);

        when(groupService.findAll()).thenReturn(data);
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/accounts/new"))
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();

        Map<String, Object> modelMapper = mvcResult.getModelAndView().getModel();
        List<GroupDTO> groupDTOResponse =  (ArrayList<GroupDTO>)modelMapper.get("groupList");
        assertEquals(groupDTO.getGroupName(), groupDTOResponse.get(0).getGroupName());
        assertEquals(groupDTO.getId(), groupDTOResponse.get(0).getId());
    }

    @Test
    void testSaveAccount() throws Exception {
        doNothing().when(accountService).saveAccount(any());
        mockMvc.perform(MockMvcRequestBuilders.post("/accounts/save")
                        .flashAttr("accountDto",accountDTO))
                .andExpect(redirectedUrl("/listaccounts"));

    }
    @Test
     void failTestSaveAccount() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/accounts/save")
                        .flashAttr("accountDto",new AccountDTO()))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void testListAccounts() throws Exception {
        List<AccountDTO> accountDTOS=new ArrayList<>();
        accountDTOS.add(accountDTO);
        when(accountService.findAll()).thenReturn(accountDTOS);
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/listaccounts"))
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();

        Map<String, Object> modelMapper = mvcResult.getModelAndView().getModel();
        List<AccountDTO> AccountDTOResponse =  (ArrayList<AccountDTO>)modelMapper.get("accountList");
        assertEquals(accountDTOS.get(0).getAccountName(),AccountDTOResponse.get(0).getAccountName());
        assertEquals(accountDTOS.get(0).getId(),AccountDTOResponse.get(0).getId());
        assertEquals(accountDTOS.get(0).getPassword(),AccountDTOResponse.get(0).getPassword());
        assertEquals(accountDTOS.get(0).getUrl(),AccountDTOResponse.get(0).getUrl());
        assertEquals(accountDTOS.get(0).getUserName(),AccountDTOResponse.get(0).getUserName());
        assertEquals(accountDTOS.get(0).getGroup().getId(),AccountDTOResponse.get(0).getGroup().getId());
        assertEquals(accountDTOS.get(0).getGroup().getGroupName(),AccountDTOResponse.get(0).getGroup().getGroupName());

    }
    @Test
    void testEditAccount() throws Exception {
        when(accountService.findById(any())).thenReturn(accountDTO);
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/account/edit/{id}",1))
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();

        Map<String, Object> modelMapper = mvcResult.getModelAndView().getModel();
        AccountDTO AccountDTOResponse =  (AccountDTO)modelMapper.get("accountDto");
        assertEquals(accountDTO.getAccountName(),AccountDTOResponse.getAccountName());
    }
    @Test
    void testDeleteAccount() throws Exception {
        doNothing().when(accountService).deleteById(anyInt());
        mockMvc.perform(MockMvcRequestBuilders.get("/account/delete/{id}",1))
                .andExpect(redirectedUrl("/listaccounts"));
    }


}

package com.example.pmt_tool.controllers;

import com.example.pmt_tool.dto.AccountDTO;
import com.example.pmt_tool.dto.GroupDTO;
import com.example.pmt_tool.controller.GroupController;
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
 class GroupControllerTest {
    @InjectMocks
    GroupController groupController;
    @Mock
    AccountService accountService;
    @Mock
    GroupService groupService;

    private MockMvc mockMvc;

    GroupDTO groupDTO;

    AccountDTO accountDTO;

    @BeforeEach
    void setup(){
        mockMvc = MockMvcBuilders.standaloneSetup(groupController).build();
        groupDTO=new GroupDTO(1,"gmail");
        accountDTO=new AccountDTO(2,"chandana","swethas","dsg@#12H3","https://www.google.com",new GroupDTO(2,"general"));
    }

    @Test
    void testListAccounts() throws Exception {
        List<GroupDTO> groupDTOS=new ArrayList<>();
        groupDTOS.add(groupDTO);
        when(groupService.findAll()).thenReturn(groupDTOS);
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/listgroups"))
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
        Map<String, Object> modelMapper = mvcResult.getModelAndView().getModel();
        List<GroupDTO> groupDTOResponse= (List<GroupDTO>) modelMapper.get("groupList");
        assertEquals(groupDTOS.get(0).getGroupName(),groupDTOResponse.get(0).getGroupName());
        assertEquals(groupDTOS.get(0).getId(),groupDTOResponse.get(0).getId());
    }

    @Test
     void  testAddGroup() throws Exception {
        doNothing().when(groupService).save(any());
        mockMvc.perform(MockMvcRequestBuilders.post("/groups/save")
                        .flashAttr("groupDTO",groupDTO))
                .andExpect(redirectedUrl("/listgroups"));
    }
    @Test
     void testEditGroup() throws Exception {
        when(groupService.findById(anyInt())).thenReturn(groupDTO);
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/group/edit/{id}",1))
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
        Map<String, Object> modelMapper = mvcResult.getModelAndView().getModel();
        GroupDTO groupDtoResponse =  (GroupDTO)modelMapper.get("groupDto");
        assertEquals(groupDTO.getGroupName(),groupDtoResponse.getGroupName());
    }
    @Test
    void testGetAccountsByGroup() throws Exception {

        List<AccountDTO> accountDTOS=new ArrayList<>();
        accountDTOS.add(accountDTO);

        when(groupService.findById(anyInt())).thenReturn(groupDTO);
        when(accountService.findAccountsByGroupId(anyInt())).thenReturn(accountDTOS);
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/group/accounts/{id}",3))
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
    void testDeleteGroup() throws Exception {
        doNothing().when(accountService).deleteAccountByGroupId(anyInt());
        doNothing().when(groupService).deleteById(anyInt());
        mockMvc.perform(MockMvcRequestBuilders.get("/group/delete/{id}",1))
                .andExpect(redirectedUrl("/listgroups"));
    }

    @Test
    void testCreateGroup() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/groups/new"))
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
    }


}

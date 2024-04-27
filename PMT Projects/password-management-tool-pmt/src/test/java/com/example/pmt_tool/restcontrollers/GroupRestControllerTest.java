package com.example.pmt_tool.restcontrollers;

import com.example.pmt_tool.dto.AccountDTO;
import com.example.pmt_tool.dto.GroupDTO;
import com.example.pmt_tool.services.AccountService;
import com.example.pmt_tool.services.GroupService;
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
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
 class GroupRestControllerTest {
    @InjectMocks
    GroupRestController groupRestController;
    @Mock
    GroupService groupService;
    @Mock
    AccountService accountService;
    @Autowired
    private MockMvc mockMvc;
    @Mock
    ObjectMapper objectMapper;
    AccountDTO accountDTO;
    GroupDTO groupDTO;
    @BeforeEach
     void setup(){
        mockMvc = MockMvcBuilders.standaloneSetup(groupRestController).build();
        objectMapper = new ObjectMapper();
        accountDTO=new AccountDTO(2,"chandana","swethas","dsg@#12H3","https://www.google.com",new GroupDTO(2,"general"));
        groupDTO=new GroupDTO(1,"social");
    }

    @Test
     void testAddGroup() throws Exception {
        doNothing().when(groupService).save(groupDTO);
        String json = objectMapper.writeValueAsString(groupDTO);
        mockMvc.perform(post("/groups").contentType(MediaType.APPLICATION_JSON)
                .content(json).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    }
    @Test
     void failTestAddGroup() throws Exception {
        GroupDTO groupDtoObj=new GroupDTO();
        String json = objectMapper.writeValueAsString(groupDtoObj);
        mockMvc.perform(post("/groups").contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
                .content(json).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    }
    @Test
     void testListGroups() throws Exception {
        List<GroupDTO> groupDTOS=new ArrayList<>();
        groupDTOS.add(groupDTO);
        when(groupService.findAll()).thenReturn(groupDTOS);
        String json = objectMapper.writeValueAsString(groupDTOS);
        mockMvc.perform(get("/groups")).andExpect(status().isOk()).andExpect(jsonPath("$", IsCollectionWithSize.hasSize(1)))
                .andExpect(jsonPath("$[0].groupName", equalTo("social")));
    }

    @Test
    void testFindGroupById() throws Exception{
        when(groupService.findById(anyInt())).thenReturn(groupDTO);
        String json = objectMapper.writeValueAsString(groupDTO);
        mockMvc.perform(get("/groups/{id}",1)).andExpect(status().isOk());
    }
    @Test
    void testDeleteGroup() throws Exception{
        doNothing().when(groupService).deleteById(anyInt());
        mockMvc.perform(delete("/groups/{id}",1)).andExpect(status().isOk());

    }
}

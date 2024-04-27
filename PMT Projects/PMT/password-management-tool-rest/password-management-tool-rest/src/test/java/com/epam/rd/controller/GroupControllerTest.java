package com.epam.rd.controller;
import com.epam.rd.restController.GroupController;
import com.epam.rd.bean.GroupBean;
import com.epam.rd.exception.DuplicateGroupException;
import com.epam.rd.exception.GroupDoesNotExistException;
import com.epam.rd.service.interfaces.GroupService;
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
@WebMvcTest(value = GroupController.class)
@WithMockUser
public class GroupControllerTest {
    @MockBean
    GroupService groupService;
    @MockBean
    JwtTokenUtil jwtTokenUtil;
    @MockBean
    UserService userService;

    @Autowired
    MockMvc mockMvc;
    GroupBean groupBean;

    @Before
    public void setUp(){
        groupBean = new GroupBean();
    }

    @Test
    @DisplayName("getGroupById Should Give Ok Status If Group Exist")
    public void getGroupByIdShouldGiveOkStatusIfGroupExist() throws Exception {
        when(groupService.getGroupById(anyInt())).thenReturn(groupBean);
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/groups/group/2")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("getAllGroups Should Give Ok status")
    public void getAllGroupsShouldGiveOkStatus() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/groups")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("getGroupById Should Give Not Found Status If Group not Exist")
    public void getGroupByIdShouldGiveNotFoundStatusIfGroupNotExist() throws Exception {
        doThrow(GroupDoesNotExistException.class).when(groupService).getGroupById(anyInt());
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/groups/group/2")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("deleteGroup Should Give Ok Status If Group Exist")
    public void deleteShouldGiveOkStatusIfGroupExist() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/groups/group/2")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("deleteGroup Should Give Not found Status If Group not Exist")
    public void deleteGroupShouldGiveNotFoundStatusIfGroupNotExist() throws Exception {
        doThrow(GroupDoesNotExistException.class).when(groupService).deleteGroupById(anyInt());
        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/groups/group/2")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("saveGroup Should Give Bad Request Status If group is invalid")
    public void saveGroupShouldGiveValidationMessagesWithBadRequestCode() throws Exception {
        GroupBean groupBean = new GroupBean("");
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/groups/group")
                        .content(asJsonString(groupBean))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("saveGroup Should Give conflict Status If group is duplicate")
    public void saveGroupShouldGiveConflictStatusCodeForDuplicateGroup() throws Exception {
        GroupBean groupBean = new GroupBean("jindalhoney");
        doThrow(DuplicateGroupException.class).when(groupService).saveGroup(any());
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/groups/group")
                        .content(asJsonString(groupBean))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isConflict());
    }

    @Test
    @DisplayName("saveGroup Should Give Ok Status If group saved")
    public void saveGroupShouldSaveGroupWithOkStatus() throws Exception {
        GroupBean groupBean = new GroupBean("jindalhoney");
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/groups/group")
                        .content(asJsonString(groupBean))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("updateGroup Should Give Bad Request Status If group is invalid")
    public void updateGroupShouldGiveValidationMessagesWithBadRequestCode() throws Exception {
        GroupBean groupBean = new GroupBean("");
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/groups/group/1")
                        .content(asJsonString(groupBean))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("updateGroup Should Give conflict Status If group is duplicate")
    public void updateGroupShouldGiveConflictStatusCodeForDuplicateGroup() throws Exception {
        GroupBean groupBean = new GroupBean("jindalhoney");
        doThrow(DuplicateGroupException.class).when(groupService).updateGroupById(anyInt(),any());
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/groups/group/1")
                        .content(asJsonString(groupBean))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isConflict());
    }

    @Test
    @DisplayName("updateGroup Should Give Ok Status If group saved")
    public void updateGroupShouldSaveGroupWithOkStatus() throws Exception {
        GroupBean groupBean = new GroupBean("jindalhoney");
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/groups/group/1")
                        .content(asJsonString(groupBean))
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

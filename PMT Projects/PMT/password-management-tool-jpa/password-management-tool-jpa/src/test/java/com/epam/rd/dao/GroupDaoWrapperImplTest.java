package com.epam.rd.dao;

import com.epam.rd.dao.interfaces.GroupDao;
import com.epam.rd.bean.GroupBean;
import com.epam.rd.bean.UserBean;
import com.epam.rd.exception.DuplicateGroupException;
import com.epam.rd.exception.GroupDoesNotExistException;
import com.epam.rd.entity.Group;
import com.epam.rd.entity.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;

import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class GroupDaoWrapperImplTest {

    @InjectMocks
    GroupDaoWrapperImpl groupDaoWrapper;
    @Mock
    GroupDao groupDao;
    @Mock
    ModelMapper modelMapper;
    GroupBean groupBean;
    Group group;
    UserBean userBean;
    User user;

    @Before
    public void setUp(){
        groupBean = new GroupBean("");
        group = new Group("");
        when(modelMapper.map(groupBean, Group.class)).thenReturn(group);
        when(modelMapper.map(group , GroupBean.class)).thenReturn(groupBean);
        when(modelMapper.map(userBean, User.class)).thenReturn(user);
    }

    @Test
    @DisplayName("save group should check for duplicate group")
    public void saveGroupShouldCheckForDuplicateGroup(){
        when(groupDao.countByGroupNameAndUser(anyString() , any())).thenReturn(1);
        Assertions.assertThrows(DuplicateGroupException.class , () -> groupDaoWrapper.saveGroup(groupBean, userBean));
    }

    @Test
    @DisplayName("save group should save Group")
    public void saveGroupShouldSaveGroup(){
        when(groupDao.countByGroupNameAndUser(anyString() , any())).thenReturn(0);
        Assertions.assertDoesNotThrow(() -> groupDaoWrapper.saveGroup(groupBean, userBean));
    }

    @Test
    @DisplayName("getALlGroups should return all available groups")
    public void getAllGroupsShouldReturnAllGroups(){
        when(groupDao.findByUser(any())).thenReturn(List.of(group,group));
        Assertions.assertEquals(List.of(groupBean, groupBean) , groupDaoWrapper.getAllGroups(userBean));
    }

    @Test
    @DisplayName("getALlGroups should return all available groups")
    public void getAllGroupsShouldReturnAllGroups1(){
        when(groupDao.findByUser(any())).thenReturn(List.of(group));
        Assertions.assertNotEquals(List.of(groupBean, groupBean) , groupDaoWrapper.getAllGroups(userBean));
    }

    @Test
    @DisplayName("getGroupById should throw exception if group not found")
    public void getGroupByIdShouldThrowExceptionIfGroupNotFound(){
        when(groupDao.findByGroupIdAndUser(anyInt(),any())).thenReturn(List.of());
        Assertions.assertThrows(GroupDoesNotExistException.class , ()->groupDaoWrapper.getGroupById(1, userBean));
    }

    @Test
    @DisplayName("getGroupById should return group if found")
    public void getGroupByIdShouldReturnGroupIfFound(){
        when(groupDao.findByGroupIdAndUser(anyInt(),any())).thenReturn(List.of(group));
        Assertions.assertDoesNotThrow(()->groupDaoWrapper.getGroupById(1, userBean));
    }

    @Test
    @DisplayName("updateGroupById should check for duplicate group name if new group name is different")
    public void updateGroupByIdShouldCheckForDuplicateGroupNameIfGroupNameIsDifferent(){
        when(groupDao.countByGroupNameAndUser(anyString() , any())).thenReturn(0);
        when(groupDao.findByGroupIdAndUser(anyInt() , any())).thenReturn(List.of(group));
        Assertions.assertDoesNotThrow(()->groupDaoWrapper.updateGroupsById(1 , new GroupBean(" sdv") , userBean));

    }

    @Test
    @DisplayName("updateGroupById should check for duplicate and throw exception id duplicate")
    public void updateGroupByIdShouldCheckForDuplicateGroupNameAndThrowExceptionIfDuplicate(){
        when(groupDao.countByGroupNameAndUser(anyString() , any())).thenReturn(1);
        when(groupDao.findByGroupIdAndUser(anyInt() , any())).thenReturn(List.of(group));
        Assertions.assertThrows(DuplicateGroupException.class,()->groupDaoWrapper.updateGroupsById(1 , new GroupBean(" sfds") , userBean));
    }

    @Test
    @DisplayName("updateGroupById should throw exception if group does not exist")
    public void updateGroupByIdShouldThrowExceptionIfGroupDoesNotExist(){
        when(groupDao.findByGroupIdAndUser(anyInt() , any())).thenReturn(List.of());
        Assertions.assertThrows(GroupDoesNotExistException.class,()->groupDaoWrapper.updateGroupsById(1 , new GroupBean(" sfds") , userBean));
    }
    @Test
    @DisplayName("deleteGroupById should throw exception if group does not exist")
    public void deleteByIdShouldThrowExceptionIfGroupDoesNotExist(){
        when(groupDao.findByGroupIdAndUser(anyInt() , any())).thenReturn(List.of());
        Assertions.assertThrows(GroupDoesNotExistException.class,()->groupDaoWrapper.deleteGroupById(1 , userBean));
    }

    @Test
    @DisplayName("deleteGroupById should delete group if exist")
    public void deleteByIdShouldDeleteGroupIfExist(){
        when(groupDao.findByGroupIdAndUser(anyInt() , any())).thenReturn(List.of(group));
        Assertions.assertDoesNotThrow(()->groupDaoWrapper.deleteGroupById(1 , userBean));
    }
}

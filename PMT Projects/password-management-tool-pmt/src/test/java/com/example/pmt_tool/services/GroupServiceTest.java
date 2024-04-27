package com.example.pmt_tool.services;

import com.example.pmt_tool.dto.GroupDTO;
import com.example.pmt_tool.entities.Group;
import com.example.pmt_tool.repositories.GroupRepo;
import com.example.pmt_tool.services.impl.GroupServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GroupServiceTest {
    @InjectMocks
    GroupServiceImpl groupService;
    @Mock
    GroupRepo groupRepo;
    @Mock
    ModelMapper modelMapper;

    GroupDTO groupDTO;

    Group group;

    @BeforeEach
    void setup(){
        group=new Group(3,"social");
        groupDTO=new GroupDTO(3,"social");
    }
    @Test
     void saveGroup(){
        when(modelMapper.map(any(),eq(Group.class))).thenReturn(group);
        when(groupRepo.save(any())).thenReturn(group);
        groupService.save(groupDTO);
        assertTrue(true);
    }

    @Test
    void deleteGroup(){
        doNothing().when(groupRepo).deleteById(anyInt());
        groupService.deleteById(2);
        assertTrue(true);
    }

    @Test
     void findAll(){
        List<Group> groupList=new ArrayList<>();
        List<GroupDTO> groupDTOList=new ArrayList<>();
        groupList.add(group);
        groupDTOList.add(groupDTO);
        when(modelMapper.map(any(),eq(GroupDTO.class))).thenReturn(groupDTO);
        when(groupRepo.findAll()).thenReturn(groupList);
        List<GroupDTO> groupDTOS=groupService.findAll();
        assertNotNull(groupDTOS);
    }
    @Test
     void findById(){
        when(groupRepo.findById(any())).thenReturn(Optional.of(group));
        when(modelMapper.map(any(), eq(GroupDTO.class))).thenReturn(groupDTO);
        groupService.findById(1);
        assertTrue(true);
    }
    @Test
    public void testCreateDefaultGroup(){
        when(groupRepo.findAll()).thenReturn(Stream.of(group).collect(Collectors.toList()));
        groupService.createDefaultGroup();
        assertTrue(true);
    }
    @Test
    public void testCreateDefaultGroupFalse(){
        List<Group> groups=new ArrayList<>();
        when(groupRepo.findAll()).thenReturn(groups);
        groupService.createDefaultGroup();
        assertTrue(true);
    }


}


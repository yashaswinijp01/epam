package com.example.pmt_tool.services.impl;

import com.example.pmt_tool.exceptions.DuplicateGroupException;
import com.example.pmt_tool.dto.GroupDTO;
import com.example.pmt_tool.entities.Group;
import com.example.pmt_tool.repositories.GroupRepo;
import com.example.pmt_tool.services.GroupService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class GroupServiceImpl implements GroupService {
    @Autowired
    GroupRepo groupRepo;
    @Autowired
    ModelMapper modelMapper;
    @Override
    public List<GroupDTO> findAll() {
        return getGroupDTOS();
    }

    private List<GroupDTO> getGroupDTOS() {
        List<Group> group=groupRepo.findAll();
        List<GroupDTO> groupDTOList=new ArrayList<>();
        for(Group group1:group){
            GroupDTO groupDTO= entityToDto(group1);
            groupDTOList.add(groupDTO);
        }
        return groupDTOList;
    }

    @Override
    public void save(GroupDTO groupDto) {
       Group group1= dtoToEntity(groupDto);
       Group groupById=groupRepo.findByGroupName(group1.getGroupName());
       if(groupById!=null){
           throw new DuplicateGroupException("Group already exists");
       }
       groupRepo.save(group1);
    }

    @Override
    public void deleteById(Integer id) {
        groupRepo.deleteById(id);
    }

    @Override
    public GroupDTO findById(Integer id) {
        Group group=groupRepo.findById(id).get();
        return entityToDto(group);
    }

    private GroupDTO entityToDto(Group group) {
        return modelMapper.map(group, GroupDTO.class);
    }

    private Group dtoToEntity(GroupDTO groupDto) {
        return modelMapper.map(groupDto, Group.class);
    }

    @PostConstruct
    public void createDefaultGroup() {
        List<Group> groups = groupRepo.findAll();
        boolean flag = true;
        for (Group group : groups) {
            if (group.getGroupName().equals("General")) {
                flag = false;
            }
        }
        if (flag) {
            Group group = new Group(1, "General");
            groupRepo.save(group);
        }
    }
}

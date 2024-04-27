package com.example.pmt_tool.restcontrollers;


import com.example.pmt_tool.dto.GroupDTO;
import com.example.pmt_tool.services.AccountService;
import com.example.pmt_tool.services.GroupService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@Slf4j
public class GroupRestController {
    @Autowired
    GroupService groupService;
    @Autowired
    AccountService accountService;

    @GetMapping("/groups")
    public ResponseEntity<List<GroupDTO>> groupDTOList(){
        List<GroupDTO> groupDTOList=groupService.findAll();
        return new ResponseEntity<>(groupDTOList, HttpStatus.OK);
    }
    @PostMapping("/groups")
    public ResponseEntity<String> saveGroup(@Valid @RequestBody GroupDTO groupDTO, Errors errors){
        if(errors.hasErrors()){
            return new ResponseEntity<>(errors.getAllErrors()+" ",HttpStatus.BAD_REQUEST);
        }
        else{
            groupService.save(groupDTO);
            return new ResponseEntity<>("Group Added",HttpStatus.OK);
        }
    }
    @GetMapping("/groups/{id}")
    public ResponseEntity<GroupDTO> getGroupById(@PathVariable("id") Integer id){
        GroupDTO groupDTO=groupService.findById(id);
        return new ResponseEntity<>(groupDTO,HttpStatus.OK);
    }
    @DeleteMapping("/groups/{id}")
    public ResponseEntity<String> deleteGroupById(@PathVariable("id") Integer id){
        accountService.deleteAccountByGroupId(id);
        groupService.deleteById(id);
        return new ResponseEntity<>("deleted",HttpStatus.OK);
    }


}
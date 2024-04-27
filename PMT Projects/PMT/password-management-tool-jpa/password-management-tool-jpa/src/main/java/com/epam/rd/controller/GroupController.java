package com.epam.rd.controller;

import com.epam.rd.bean.GroupBean;
import com.epam.rd.exception.DuplicateGroupException;
import com.epam.rd.exception.GroupDoesNotExistException;
import com.epam.rd.service.interfaces.GroupService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
public class GroupController {
    @Autowired
    GroupService groupService;

    @GetMapping("/groups/group")
    public String addGroup(@ModelAttribute("groupBean") GroupBean groupBean, ModelMap model){
        model.put("head" , "Add Group");
        return "addGroup";
    }

    @GetMapping("/groups")
    public String getAllGroups(ModelMap model){
        List<GroupBean> groups = groupService.getAllGroups();
        model.put("allGroups" , groups);
        return "viewAccounts";
    }

    @PostMapping("/groups/group")
    public String saveGroup(@Valid @ModelAttribute("groupBean") GroupBean groupBean, BindingResult bindingResult , ModelMap model) {
        model.put("head" , "Add Group");
        if(bindingResult.hasErrors()){
            return "addGroup";
        }
        try {
            groupService.saveGroup(groupBean);
        } catch (DuplicateGroupException e) {
            model.put("errorMessage" , e.getMessage());
            return "addGroup";
        }
        return "redirect:/groups";
    }

    @PostMapping("/groups/group/delete/{groupId}")
    public String deleteAccount(@PathVariable int groupId)throws GroupDoesNotExistException {
        groupService.deleteGroupById(groupId);
        return "redirect:/groups";
    }

    @GetMapping("/groups/group/{groupId}")
    public String getGroupById(@PathVariable int groupId, @ModelAttribute("groupBean") GroupBean groupBean, ModelMap model) throws GroupDoesNotExistException {
        model.put("head", "Update Group");
        GroupBean groupBean1 = groupService.getGroupById(groupId);
        BeanUtils.copyProperties(groupBean1, groupBean);
        return "addGroup";
    }

    @PostMapping("/groups/group/{groupId}")
    public String saveUpdatedGroup(@PathVariable int groupId , @Valid @ModelAttribute("groupBean") GroupBean groupBean, BindingResult bindingResult , ModelMap model) throws GroupDoesNotExistException {
        model.put("head" , "Update Group");
        if(bindingResult.hasErrors()){
            return "addGroup";
        }
        try {
            groupService.updateGroupById(groupId , groupBean);
        } catch (DuplicateGroupException e) {
            model.put("errorMessage" , e.getMessage());
            return "addGroup";
        }
        return "redirect:/groups";
    }


    @ExceptionHandler(GroupDoesNotExistException.class)
    public String groupDoesNotExistHandler(GroupDoesNotExistException exception){
        return "error";
    }

}

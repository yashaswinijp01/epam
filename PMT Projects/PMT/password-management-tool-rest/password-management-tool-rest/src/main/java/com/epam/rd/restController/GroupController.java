package com.epam.rd.restController;

import com.epam.rd.bean.GroupBean;
import com.epam.rd.exception.DuplicateGroupException;
import com.epam.rd.exception.GroupDoesNotExistException;
import com.epam.rd.service.interfaces.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class GroupController {
    @Autowired
    GroupService groupService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/groups")
    public List<GroupBean> getGroups(){
        return groupService.getAllGroups();
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/groups/group")
    public void saveGroup(@Valid @RequestBody GroupBean groupBean) throws DuplicateGroupException {
        groupService.saveGroup(groupBean);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/groups/group/{groupId}")
    public GroupBean updateGroup(@PathVariable int groupId) throws GroupDoesNotExistException {
        return groupService.getGroupById(groupId);
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/groups/group/{groupId}")
    public void saveUpdatedGroup(@PathVariable int groupId , @Valid @RequestBody GroupBean groupBean) throws GroupDoesNotExistException, DuplicateGroupException {
        groupService.updateGroupById(groupId , groupBean);
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/groups/group/{groupId}")
    public void deleteGroup(@PathVariable int groupId) throws GroupDoesNotExistException {
        groupService.deleteGroupById(groupId);
    }




    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(DuplicateGroupException.class)
    public Map<String , String> duplicateGroupHandler(DuplicateGroupException duplicateGroupException){
        Map <String , String > map = new HashMap<>();
        map.put("duplicate",duplicateGroupException.getMessage());
        return map;
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(GroupDoesNotExistException.class)
    public String groupNotExistHandler(GroupDoesNotExistException exception){
        return exception.getMessage();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

}

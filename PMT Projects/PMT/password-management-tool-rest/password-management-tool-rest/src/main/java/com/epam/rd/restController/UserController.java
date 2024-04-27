package com.epam.rd.restController;

import com.epam.rd.bean.UserBean;
import com.epam.rd.exception.DuplicateUserException;
import com.epam.rd.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
public class UserController {

    @Autowired
    UserService userService;


    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/register")
    public void saveUser(@Valid @RequestBody UserBean userBean) throws DuplicateUserException {
        userService.saveUser(userBean);
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(value = DuplicateUserException.class)
    public Map<String , String> duplicateUserExceptionHandler(DuplicateUserException exception){
        Map <String , String > map = new HashMap<>();
        map.put("duplicate",exception.getMessage());
        return map;
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

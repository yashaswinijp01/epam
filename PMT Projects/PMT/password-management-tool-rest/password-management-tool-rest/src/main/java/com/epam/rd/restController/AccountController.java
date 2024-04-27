package com.epam.rd.restController;

import com.epam.rd.bean.AccountBean;
import com.epam.rd.exception.AccountDoesNotExistException;
import com.epam.rd.exception.DuplicateAccountException;
import com.epam.rd.service.interfaces.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.*;

@RestController
public class AccountController {

    @Autowired
    AccountService accountService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/accounts/account/{accountId}")
    public AccountBean getAccountById(@PathVariable int accountId) throws AccountDoesNotExistException {
        return accountService.getAccountById(accountId);
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/accounts/account")
    public void saveAccount(@Valid @RequestBody AccountBean accountBean) throws DuplicateAccountException {
        accountService.saveAccount(accountBean);
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/accounts/account/{accountId}")
    public void saveUpdatedAccount(@PathVariable int accountId , @Valid @RequestBody AccountBean accountBean) throws AccountDoesNotExistException, DuplicateAccountException {
        accountService.updateAccountById(accountBean, accountId);
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/accounts/account/{accountId}")
    public void deleteAccount(@PathVariable int accountId) throws AccountDoesNotExistException {
        accountService.removeAccountById(accountId);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(AccountDoesNotExistException.class)
    public String accountDoesNotExistHandler(AccountDoesNotExistException exception){
        return exception.getMessage();
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(DuplicateAccountException.class)
    public Map<String , String> duplicateAccountHandler(DuplicateAccountException exception){
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

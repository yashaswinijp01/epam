package com.example.pmt_tool.restcontrollers;


import com.example.pmt_tool.dto.AccountDTO;
import com.example.pmt_tool.exceptions.AccountNotFoundException;
import com.example.pmt_tool.services.AccountService;
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
public class AccountRestController {
    @Autowired
    AccountService accountService;

    @PostMapping("/accounts")
    public ResponseEntity<String> saveAccount(@Valid @RequestBody AccountDTO accountDTO, Errors errors){
        if(errors.hasErrors()){
            return new ResponseEntity<>(errors.getAllErrors()+" ",HttpStatus.BAD_REQUEST);
        }else{
            accountService.saveAccount(accountDTO);
            return new ResponseEntity<>("Added successfully",HttpStatus.OK);
        }
    }

    @GetMapping("/accounts")
    public ResponseEntity<List<AccountDTO>> getAccounts(){
        List<AccountDTO> accountDTOSList= accountService.findAll();
        return new ResponseEntity<>(accountDTOSList,HttpStatus.OK);
    }

    @DeleteMapping("/accounts/{id}")
    public ResponseEntity<String> deleteAccount(@PathVariable("id") Integer id) throws AccountNotFoundException {
        accountService.deleteById(id);
        return new ResponseEntity<>("deleted",HttpStatus.OK);
    }

    @GetMapping("/accounts/groups/{id}")
    public ResponseEntity<List<AccountDTO>> findAccountsByGroupId(@PathVariable("id") Integer id){
        List<AccountDTO> accountDTOList=accountService.findAccountsByGroupId(id);
        return new ResponseEntity<>(accountDTOList,HttpStatus.OK);
    }
    @GetMapping("/accounts/{id}")
    public ResponseEntity<AccountDTO> findAccountById(@PathVariable("id") Integer id) throws AccountNotFoundException {
       AccountDTO accountDTO= accountService.findById(id);
        return new ResponseEntity<>(accountDTO , HttpStatus.OK);
    }
    @DeleteMapping("/accounts/group/{id}")
    public ResponseEntity<String> deleteAccountByGroupId(@PathVariable("id") Integer id) throws AccountNotFoundException{
        accountService.deleteAccountByGroupId(id);
        return new ResponseEntity<>("deleted",HttpStatus.OK);
    }
}

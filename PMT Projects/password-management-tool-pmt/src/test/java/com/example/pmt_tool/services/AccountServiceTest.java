package com.example.pmt_tool.services;

import com.example.pmt_tool.dto.AccountDTO;
import com.example.pmt_tool.dto.GroupDTO;
import com.example.pmt_tool.exceptions.AccountNotFoundException;
import com.example.pmt_tool.entities.Account;
import com.example.pmt_tool.entities.Group;
import com.example.pmt_tool.repositories.AccountRepo;
import com.example.pmt_tool.services.impl.AccountServiceImpl;
import org.junit.jupiter.api.Assertions;
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

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AccountServiceTest {
    @InjectMocks
    AccountServiceImpl accountService;
    @Mock
    AccountRepo accountRepo;
    @Mock
    ModelMapper modelMapper;

    AccountDTO accountDTO;
    Account expectedAccount;
    AccountDTO expectedAccountDto;

    @BeforeEach
    void setup(){
        accountDTO=new AccountDTO(2,"swetha","sw123","12aw","www.gogle.com",new GroupDTO(2,"general"));
        expectedAccount=new Account(2,"swetha","sw123","12aw","www.gogle.com",new Group(2,"general"));
        expectedAccountDto=new AccountDTO(2,"swetha","sw123","12aw","www.gogle.com",new GroupDTO(3,"spring"));
    }

    @Test
     void saveAccountData(){
        when(modelMapper.map(any(),eq(Account.class))).thenReturn(expectedAccount);
        when(accountRepo.save(any())).thenReturn(expectedAccount);
        accountService.saveAccount(accountDTO);
        assertTrue(true);
    }
    @Test
    void findAccountById() throws AccountNotFoundException {
        when(accountRepo.findById(anyInt())).thenReturn(Optional.of(expectedAccount));
        when(modelMapper.map(any(), eq(AccountDTO.class))).thenReturn(expectedAccountDto);
        AccountDTO actualResponse=accountService.findById(3);
        assertNotNull(actualResponse);
    }
    @Test
    void getAccounts(){
        List<Account> accounts=new ArrayList<>();
        accounts.add(expectedAccount);
        List<AccountDTO> accountDTOList=new ArrayList<>();
        accountDTOList.add(accountDTO);
        when(modelMapper.map(any(),eq(AccountDTO.class))).thenReturn(accountDTO);
        when(accountRepo.findAll()).thenReturn(accounts);
        List<AccountDTO> accountDTOS=accountService.findAll();
        assertNotNull(accountDTOS);
    }

    @Test
     void deleteAccount() throws AccountNotFoundException {
        when(accountRepo.findById(anyInt())).thenReturn(Optional.of(expectedAccount));
        doNothing().when(accountRepo).deleteById(anyInt());
        accountService.deleteById(2);
        assertTrue(true);
    }
    @Test
     void deleteAccountsByGroupId(){
        List<Account> accounts=new ArrayList<>();
        accounts.add(expectedAccount);
        when(accountRepo.findAccountsByGroupId(anyInt())).thenReturn(accounts);
        accountService.deleteAccountByGroupId(2);
        accountService.deleteAccountByGroupId(3);
        assertTrue(true);

    }
    @Test
     void findAccountByGroupId(){
        List<Account> accounts=new ArrayList<>();
        accounts.add(expectedAccount);
        List<AccountDTO> accountDTOList=new ArrayList<>();
        accountDTOList.add(accountDTO);
        when(accountRepo.findAccountsByGroupId(anyInt())).thenReturn(accounts);
        List<AccountDTO> accountDTOS=accountService.findAccountsByGroupId(3);
        assertNotNull(accountDTOS);

    }

    @Test
    void whenIdNotFoundThrowException() throws AccountNotFoundException {
        when(accountRepo.findById(anyInt())).thenReturn(Optional.empty());
        Assertions.assertThrows(AccountNotFoundException.class,() -> accountService.findById(1));
    }

    @Test
    void whenIdNotFoundForDeletionThrowException() throws AccountNotFoundException {
        when(accountRepo.findById(anyInt())).thenReturn(Optional.empty());
        Assertions.assertThrows(AccountNotFoundException.class,() -> accountService.deleteById(1));
    }

}

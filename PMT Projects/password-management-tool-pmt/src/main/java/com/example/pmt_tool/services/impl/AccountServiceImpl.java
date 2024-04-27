package com.example.pmt_tool.services.impl;

import com.example.pmt_tool.dto.AccountDTO;
import com.example.pmt_tool.exceptions.AccountNotFoundException;
import com.example.pmt_tool.entities.Account;
import com.example.pmt_tool.repositories.AccountRepo;
import com.example.pmt_tool.services.AccountService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    AccountRepo accountRepo;

    @Override
    public void saveAccount(AccountDTO accountDto) {
    Account account= dtoToEntity(accountDto);
    accountRepo.save(account);
    }

    public void deleteById(Integer id) throws AccountNotFoundException {
        Optional<Account> account= accountRepo.findById(id);
        if(account.isEmpty()){
            throw new AccountNotFoundException("Account Not Found");
        }
        else{
            accountRepo.deleteById(id);
        }
    }

    @Override
    public void deleteAccountByGroupId(Integer id) {
        List<Account> accountList=accountRepo.findAccountsByGroupId(id);
        accountRepo.deleteAll(accountList);
    }

    @Override
    public List<AccountDTO> findAccountsByGroupId(Integer id) {
       List<Account> accountList=accountRepo.findAccountsByGroupId(id);
        return getAccountDTOS(accountList);
    }

    private List<AccountDTO> getAccountDTOS(List<Account> accountList) {
        List<AccountDTO> accountDTOList=new ArrayList<>();
        for(Account account:accountList){
            AccountDTO accountDTO= entityToDto(account);
            accountDTOList.add(accountDTO);
        }
        return accountDTOList;
    }

    @Override
    public List<AccountDTO> findAll() {
      List<Account> accountList=accountRepo.findAll();
      return getAccountDTOS(accountList);
    }

    @Override
    public AccountDTO findById(Integer id) throws AccountNotFoundException {
        Optional<Account> account= accountRepo.findById(id);
        if(account.isEmpty()){
           throw new AccountNotFoundException("Account Not found exception");
        }
        return entityToDto(account.get());
    }

     public AccountDTO entityToDto(Account account) {
        return modelMapper.map(account, AccountDTO.class);
    }

     public Account dtoToEntity(AccountDTO account) {
        return modelMapper.map(account,Account.class);
    }
}

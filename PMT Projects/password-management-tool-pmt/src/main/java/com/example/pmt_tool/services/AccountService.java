package com.example.pmt_tool.services;

import com.example.pmt_tool.dto.AccountDTO;
import com.example.pmt_tool.exceptions.AccountNotFoundException;

import java.util.List;

public interface AccountService {
    void saveAccount(AccountDTO account);

    void deleteById(Integer id) throws AccountNotFoundException;

    void deleteAccountByGroupId(Integer id);

    List<AccountDTO> findAccountsByGroupId(Integer id);

    List<AccountDTO> findAll();

    AccountDTO findById(Integer id) throws AccountNotFoundException;


}

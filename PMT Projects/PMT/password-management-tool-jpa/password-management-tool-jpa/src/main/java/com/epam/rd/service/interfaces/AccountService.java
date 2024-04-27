package com.epam.rd.service.interfaces;

import com.epam.rd.bean.AccountBean;
import com.epam.rd.exception.AccountDoesNotExistException;
import com.epam.rd.exception.DuplicateAccountException;
import java.util.List;

public interface AccountService {
    void saveAccount(AccountBean accountBean) throws DuplicateAccountException;
    AccountBean getAccountById(int accountId) throws AccountDoesNotExistException;
    List<AccountBean> getAllAccount();
    void updateAccountById(AccountBean accountBean, int accountId) throws AccountDoesNotExistException, DuplicateAccountException;
    void removeAccountById(int accountId) throws AccountDoesNotExistException;

    List<AccountBean> getUnassignedAccounts();
}

package com.epam.rd.dao.interfaces;

import com.epam.rd.bean.AccountBean;
import com.epam.rd.bean.UserBean;
import com.epam.rd.exception.AccountDoesNotExistException;
import com.epam.rd.exception.DuplicateAccountException;
import java.util.List;

public interface AccountDaoWrapper {
    void saveAccount(AccountBean accountBean, UserBean userBean) throws DuplicateAccountException;
    AccountBean getAccountById(int accountId , UserBean userBean) throws AccountDoesNotExistException;
    List<AccountBean> getAllAccounts(UserBean userBean);
    void removeAccountById(int accountId , UserBean userBean) throws AccountDoesNotExistException;
    void updateAccountById(AccountBean account, int accountId , UserBean userBean) throws AccountDoesNotExistException, DuplicateAccountException;

    List<AccountBean> getUnAssignedAccounts(UserBean userBean);
}

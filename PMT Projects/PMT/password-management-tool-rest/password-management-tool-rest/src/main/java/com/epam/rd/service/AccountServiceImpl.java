package com.epam.rd.service;

import com.epam.rd.bean.AccountBean;
import com.epam.rd.bean.UserBean;
import com.epam.rd.dao.interfaces.AccountDaoWrapper;
import com.epam.rd.exception.AccountDoesNotExistException;
import com.epam.rd.exception.DuplicateAccountException;
import com.epam.rd.service.interfaces.AccountService;
import com.epam.rd.service.interfaces.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import java.util.*;

@Service("accountService")
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountDaoWrapper accountDaoWrapper;
    @Autowired
    private UserService userService;
    private static final Logger logger
            = LoggerFactory.getLogger(AccountServiceImpl.class);


    @Override
    public void saveAccount(AccountBean accountBean) throws DuplicateAccountException {
        logger.info("account saved");
        accountBean.setCreatedAt(new Date());
        accountBean.setLastModifiedAt(new Date());
        accountBean.setUser(getUserBean());
        setNullToUnAssignedGroup(accountBean);
        accountDaoWrapper.saveAccount(accountBean , getUserBean());
        logger.info("account saved successfully");
    }

    @Override
    public List<AccountBean> getAllAccount() {
        logger.info("Getting all accounts");
        return accountDaoWrapper.getAllAccounts(getUserBean());
    }

    @Override
    public AccountBean getAccountById(int accountId) throws AccountDoesNotExistException {
        logger.info("Getting account by Id ");
        return accountDaoWrapper.getAccountById(accountId , getUserBean());
    }

    @Override
    public void updateAccountById(AccountBean accountBean, int accountId) throws AccountDoesNotExistException, DuplicateAccountException {
        logger.info("updating the account");
        accountBean.setLastModifiedAt(new Date());
        setNullToUnAssignedGroup(accountBean);
        accountDaoWrapper.updateAccountById(accountBean , accountId , getUserBean());
        logger.info("account updated successfully");
    }

    @Override
    public void removeAccountById(int accountId) throws AccountDoesNotExistException {
        logger.info("removing account by Id");
        accountDaoWrapper.removeAccountById(accountId , getUserBean());
        logger.info("account removed by id successfully");
    }

    @Override
    public List<AccountBean> getUnassignedAccounts() {
        return accountDaoWrapper.getUnAssignedAccounts(getUserBean());
    }

    private UserBean getUserBean() {
        logger.info("Getting user bean");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return userService.getUserByUserName(authentication.getName());
    }

    private void setNullToUnAssignedGroup(AccountBean accountBean) {
        if (accountBean.getGroup() != null && accountBean.getGroup().getGroupId() == 0) {
            logger.info("setting group to null");
            accountBean.setGroup(null);
        }
    }
}

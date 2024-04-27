package com.epam.rd.dao;

import com.epam.rd.bean.AccountBean;
import com.epam.rd.bean.UserBean;
import com.epam.rd.dao.interfaces.AccountDao;
import com.epam.rd.dao.interfaces.AccountDaoWrapper;
import com.epam.rd.exception.AccountDoesNotExistException;
import com.epam.rd.exception.DuplicateAccountException;
import com.epam.rd.entity.Account;
import com.epam.rd.entity.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository("accountDaoWrapper")
public class AccountDaoWrapperImpl implements AccountDaoWrapper {
    @Autowired
    private AccountDao accountDao;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public void saveAccount(AccountBean accountBean, UserBean userBean) throws DuplicateAccountException {
        Account account = modelMapper.map(accountBean, Account.class);
        User user = getUserFromBean(userBean);
        checkForDuplicate(account.getAccountName(), user);
        accountDao.save(account);
    }

    private User getUserFromBean(UserBean userBean) {

        return modelMapper.map(userBean, User.class);
    }

    private void checkForDuplicate(String accountName, User user) throws DuplicateAccountException {
        if(accountDao.countByAccountNameAndUser(accountName, user)>0){
            throw new DuplicateAccountException("Account Already Exist");
        }
    }

    @Override
    public AccountBean getAccountById(int accountId, UserBean userBean) throws AccountDoesNotExistException {
        User user = getUserFromBean(userBean);
        List<Account> accounts = accountDao.findByAccountIdAndUser(accountId , user);
        if(accounts.isEmpty()){
            throw new AccountDoesNotExistException("Account Does not Exist with Account Id "+accountId);
        }
        return modelMapper.map(accounts.get(0) , AccountBean.class);
    }

    @Override
    public List<AccountBean> getAllAccounts(UserBean userBean) {
        User user = getUserFromBean(userBean);
        List<Account> accounts = accountDao.findByUser(user);
        List<AccountBean> accountBeans = new ArrayList<>();
        accounts.forEach(account -> accountBeans.add(modelMapper.map(account , AccountBean.class)));
        return accountBeans;
    }

    @Override
    public void removeAccountById(int accountId, UserBean userBean) throws AccountDoesNotExistException {
        AccountBean accountBean = getAccountById(accountId , userBean);
        Account account = modelMapper.map(accountBean, Account.class);
        accountDao.delete(account);
    }

    @Override
    public void updateAccountById(AccountBean account, int accountId, UserBean userBean) throws AccountDoesNotExistException, DuplicateAccountException {
        AccountBean accountBean = getAccountById(accountId , userBean);
        User user = modelMapper.map(userBean, User.class);
        if(!accountBean.getAccountName().equals(account.getAccountName())){
            checkForDuplicate(account.getAccountName() , user);
        }
        copyProperties(account, accountBean);
        accountBean.setAccountName(account.getAccountName());
        accountBean.setUserName(account.getUserName());
        accountBean.setPassword(account.getPassword());
        accountBean.setUrl(account.getUrl());
        accountBean.setGroup(account.getGroup());
        accountBean.setLastModifiedAt(account.getLastModifiedAt());
        Account account1 = modelMapper.map(accountBean, Account.class);
        accountDao.save(account1);
    }

    @Override
    public List<AccountBean> getUnAssignedAccounts(UserBean userBean) {
        User user = getUserFromBean(userBean);
        List<Account> accounts = accountDao.findByUserAndGroup(user , null);
        List<AccountBean> accountBeans = new ArrayList<>();
        accounts.forEach(account -> accountBeans.add(modelMapper.map(account , AccountBean.class)));
        return accountBeans;

    }

    private void copyProperties(AccountBean account, AccountBean accountBean) {
        accountBean.setAccountName(account.getAccountName());
        accountBean.setUserName(account.getUserName());
        accountBean.setPassword(account.getPassword());
        accountBean.setUrl(account.getUrl());
        accountBean.setGroup(account.getGroup());
        accountBean.setLastModifiedAt(account.getLastModifiedAt());
    }

}

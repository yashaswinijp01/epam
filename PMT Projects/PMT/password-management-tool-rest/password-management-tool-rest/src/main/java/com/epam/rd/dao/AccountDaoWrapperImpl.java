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
    public List<AccountBean> getUnAssignedAccounts(UserBean userBean) {
        User user = getUserFromBean(userBean);
        List<Account> accounts = accountDao.findByUserAndGroup(user , null);
        List<AccountBean> accountBeans = new ArrayList<>();
        accounts.forEach(account -> accountBeans.add(modelMapper.map(account , AccountBean.class)));
        return accountBeans;
    }

    @Override
    public void removeAccountById(int accountId, UserBean userBean) throws AccountDoesNotExistException {
        AccountBean accountBean = getAccountById(accountId , userBean);
        Account account = modelMapper.map(accountBean , Account.class);
        accountDao.delete(account);
    }

    @Override
    public void updateAccountById(AccountBean accountBean, int accountId, UserBean userBean) throws AccountDoesNotExistException, DuplicateAccountException {
        AccountBean accountBean1 = getAccountById(accountId , userBean);
        User user = modelMapper.map(userBean, User.class);
        if(!accountBean1.getAccountName().equals(accountBean.getAccountName())){
            checkForDuplicate(accountBean.getAccountName() , user);
        }
        copyProperties(accountBean, accountBean1);
        Account account1 = modelMapper.map(accountBean1, Account.class);
        accountDao.save(account1);
    }

    private void copyProperties(AccountBean accountBean, AccountBean accountBean1) {
        accountBean1.setAccountName(accountBean.getAccountName());
        accountBean1.setUserName(accountBean.getUserName());
        accountBean1.setPassword(accountBean.getPassword());
        accountBean1.setUrl(accountBean.getUrl());
        accountBean1.setGroup(accountBean.getGroup());
        accountBean1.setLastModifiedAt(accountBean.getLastModifiedAt());
    }

}

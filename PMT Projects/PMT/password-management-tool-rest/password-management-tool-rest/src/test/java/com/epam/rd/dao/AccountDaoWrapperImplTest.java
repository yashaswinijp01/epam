package com.epam.rd.dao;

import com.epam.rd.dao.interfaces.AccountDao;
import com.epam.rd.bean.AccountBean;
import com.epam.rd.bean.UserBean;
import com.epam.rd.exception.AccountDoesNotExistException;
import com.epam.rd.exception.DuplicateAccountException;
import com.epam.rd.entity.Account;
import com.epam.rd.entity.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;
import java.util.List;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AccountDaoWrapperImplTest {

    @InjectMocks
    AccountDaoWrapperImpl accountDaoWrapper;
    @Mock
    AccountDao accountDao;
    @Mock
    ModelMapper modelMapper;
    AccountBean accountBean;
    Account account;
    UserBean userBean;
    User user;

    @Before
    public void setUp(){
        accountBean = new AccountBean("" , "" , "" ,"");
        account = new Account("","","","");
        when(modelMapper.map(accountBean, Account.class)).thenReturn(account);
        when(modelMapper.map(account, AccountBean.class)).thenReturn(accountBean);
        when(modelMapper.map(userBean, User.class)).thenReturn(user);
    }

    @Test
    @DisplayName("save account should check for duplicate account")
    public void saveAccountShouldCheckForDuplicateAccount(){
        when(accountDao.countByAccountNameAndUser(anyString() , any())).thenReturn(1);
        Assertions.assertThrows(DuplicateAccountException.class , () -> accountDaoWrapper.saveAccount(accountBean, userBean));
    }

    @Test
    @DisplayName("save account should save account")
    public void saveAccountShouldSaveAccount(){
        when(accountDao.countByAccountNameAndUser(anyString() , any())).thenReturn(0);
        Assertions.assertDoesNotThrow(() -> accountDaoWrapper.saveAccount(accountBean, userBean));
    }

    @Test
    @DisplayName("getALlAccount should return all available accounts")
    public void getAllAccountsShouldReturnAllAccounts(){
        when(accountDao.findByUser(any())).thenReturn(List.of(account, account));
        Assertions.assertEquals(List.of(accountBean, accountBean) , accountDaoWrapper.getAllAccounts(userBean));
    }

    @Test
    @DisplayName("getALlAccounts should return all available account")
    public void getAllAccountsShouldReturnAllAccount1(){
        when(accountDao.findByUser(any())).thenReturn(List.of(account));
        Assertions.assertNotEquals(List.of(accountBean, accountBean) , accountDaoWrapper.getAllAccounts(userBean));
    }

    @Test
    @DisplayName("getAccountById should throw exception if account not found")
    public void getAccountByIdShouldThrowExceptionIfAccountNotFound(){
        when(accountDao.findByAccountIdAndUser(anyInt(),any())).thenReturn(List.of());
        Assertions.assertThrows(AccountDoesNotExistException.class , ()-> accountDaoWrapper.getAccountById(1, userBean));
    }

    @Test
    @DisplayName("getAccountById should return account if found")
    public void getAccountByIdShouldReturnAccountIfFound(){
        when(accountDao.findByAccountIdAndUser(anyInt(),any())).thenReturn(List.of(account));
        Assertions.assertDoesNotThrow(()-> accountDaoWrapper.getAccountById(1, userBean));
    }

    @Test
    @DisplayName("updateAccountById should check for duplicate account name if new account name is different")
    public void updateAccountByIdShouldCheckForDuplicateAccountNameIfAccountNameIsDifferent(){
        when(accountDao.countByAccountNameAndUser(anyString() , any())).thenReturn(0);
        when(accountDao.findByAccountIdAndUser(anyInt() , any())).thenReturn(List.of(account));
        Assertions.assertDoesNotThrow(()-> accountDaoWrapper.updateAccountById( new AccountBean(" sdv","","","") ,1, userBean));

    }

    @Test
    @DisplayName("updateAccountById should check for duplicate and throw exception if duplicate")
    public void updateAccountByIdShouldCheckForDuplicateAccountNameAndThrowExceptionIfDuplicate(){
        when(accountDao.countByAccountNameAndUser(anyString() , any())).thenReturn(1);
        when(accountDao.findByAccountIdAndUser(anyInt() , any())).thenReturn(List.of(account));
        Assertions.assertThrows(DuplicateAccountException.class,()-> accountDaoWrapper.updateAccountById(new AccountBean(" sdv","","","") ,1, userBean));
    }

    @Test
    @DisplayName("updateAccountById should throw exception if account does not exist")
    public void updateAccountByIdShouldThrowExceptionIfAccountDoesNotExist(){
        when(accountDao.findByAccountIdAndUser(anyInt() , any())).thenReturn(List.of());
        Assertions.assertThrows(AccountDoesNotExistException.class,()-> accountDaoWrapper.updateAccountById(new AccountBean(" sdv","","","") ,1, userBean));
    }
    @Test
    @DisplayName("deleteAccountById should throw exception if account does not exist")
    public void deleteByIdShouldThrowExceptionIfAccountDoesNotExist(){
        when(accountDao.findByAccountIdAndUser(anyInt() , any())).thenReturn(List.of());
        Assertions.assertThrows(AccountDoesNotExistException.class,()-> accountDaoWrapper.removeAccountById(1 , userBean));
    }

    @Test
    @DisplayName("deleteAccountById should delete account if exist")
    public void deleteByIdShouldDeleteAccountIfExist(){
        when(accountDao.findByAccountIdAndUser(anyInt() , any())).thenReturn(List.of(account));
        Assertions.assertDoesNotThrow(()-> accountDaoWrapper.removeAccountById(1 , userBean));
    }
    @Test
    @DisplayName("getUnAssignedAccounts should return all available unassigned accounts")
    public void getUnAssignedAccountsShouldReturnNullGroupAccounts(){
        when(accountDao.findByUserAndGroup(user , null)).thenReturn(List.of(account, account));
        Assertions.assertEquals(List.of(accountBean, accountBean) , accountDaoWrapper.getUnAssignedAccounts(userBean));
    }
}

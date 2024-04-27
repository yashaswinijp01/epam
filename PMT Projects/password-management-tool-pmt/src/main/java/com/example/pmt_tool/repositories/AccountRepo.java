package com.example.pmt_tool.repositories;

import com.example.pmt_tool.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import javax.transaction.Transactional;
import java.util.List;

public interface AccountRepo extends JpaRepository<Account,Integer> {
    @Modifying
    @Transactional
    @Query("delete from Account a where a.group.id=?1")
    void deleteAccountByGroupId(Integer id);


    @Modifying
    @Transactional
    @Query("select a from Account a where a.group.id=?1")
    List<Account> findAccountsByGroupId(Integer id);
}

package com.epam.rd.dao.interfaces;

import com.epam.rd.entity.Account;
import com.epam.rd.entity.Group;
import com.epam.rd.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;
import java.util.List;

@Repository("accountDao")
@Transactional
public interface AccountDao extends CrudRepository<Account, Integer> {
    List<Account> findByAccountNameAndUser(String accountName , User user);
    int countByAccountNameAndUser(String accountName , User user);
    List<Account> findByAccountIdAndUser(int accountId , User user);
    List<Account> findByUser(User user);

    List<Account> findByUserAndGroup(User user, Group group);
}

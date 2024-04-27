package com.epam.rd.dao.interfaces;

import com.epam.rd.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository("userDao")
@Transactional
public interface UserDao extends CrudRepository<User , Integer> {
    List<User> findByUserName(String userName);
    int countByUserName(String userName);
}

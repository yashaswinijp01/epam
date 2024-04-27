package com.epam.users.dao;

import com.epam.users.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User, Integer> {
    User findByUsername(String username);
}

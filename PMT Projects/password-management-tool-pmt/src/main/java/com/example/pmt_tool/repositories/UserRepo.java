package com.example.pmt_tool.repositories;
import com.example.pmt_tool.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User,Integer> {
    User findByUserName(String userName);
}

package com.example.pmt_tool.repositories;
import com.example.pmt_tool.entities.Group;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupRepo extends JpaRepository<Group,Integer> {

    Group findByGroupName(String groupName);
}

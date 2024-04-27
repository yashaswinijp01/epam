package com.epam.rd.dao.interfaces;

import com.epam.rd.entity.Group;
import com.epam.rd.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public interface GroupDao extends CrudRepository<Group , Integer> {
    int countByGroupNameAndUser(String groupName, User user);
    List<Group> findByUser(User user);
    List<Group> findByGroupIdAndUser(int groupId , User user);
}

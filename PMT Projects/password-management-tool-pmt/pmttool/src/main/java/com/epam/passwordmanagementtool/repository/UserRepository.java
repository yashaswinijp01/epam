package com.epam.passwordmanagementtool.repository;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.epam.passwordmanagementtool.entity.User;

@Repository
public interface UserRepository extends CrudRepository<User,Integer> {

	@Query("select u from User u where u.userName=?1 and u.password=?2")
	public User findByUserNameAndPassword(String userName,String password);

}

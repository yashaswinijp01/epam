package com.epam.passwordmanagementtool.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import com.epam.passwordmanagementtool.entity.Account;


@Repository
public interface AccountRepository extends JpaRepository<Account,Integer> {
	
	  @Modifying	  
	  @Transactional	  
	  @Query("delete from Account a where a.category.categoryId=?1") 
	  void deleteAccountByCategoryId(Integer categoryId);
	 
	
	@Query("select a from Account a where a.category.categoryName=?1")
	List<Account> findAccountsByCategoryName(String categoryName);
	
	

}


package com.epam.passwordmanagementtool.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.epam.passwordmanagementtool.entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
	
	
	

}

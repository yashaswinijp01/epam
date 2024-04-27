package com.epam.library.dao;

import com.epam.library.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookDao extends JpaRepository<Book, Integer> {

}

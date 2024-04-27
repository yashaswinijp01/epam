package com.epam.books.dao;

import com.epam.books.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookDao extends JpaRepository<Book, Integer> {


}

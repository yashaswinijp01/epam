package com.epam.library.dao;

import com.epam.library.entity.Library;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LibraryDao extends JpaRepository<Library, Integer> {
    Library findByUsernameAndBookId(String username, int bookId);
    void deleteByUsernameAndBookId(String username, int bookId);
}

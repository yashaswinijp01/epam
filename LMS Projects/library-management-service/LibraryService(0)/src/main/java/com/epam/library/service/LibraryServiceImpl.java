package com.epam.library.service;

import com.epam.library.dao.LibraryDaoWrapper;
import com.epam.library.entity.Library;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class LibraryServiceImpl implements LibraryService{
    @Autowired
    LibraryDaoWrapper libraryDaoWrapper;
    @Override
    public Library save(Library library) {

        return libraryDaoWrapper.save(library);
    }

    @Override
    public Library deleteByUsernameAndBookId(String username, int bookId) {
        return libraryDaoWrapper.deleteByUsernameAndBookId(username, bookId);
    }
}

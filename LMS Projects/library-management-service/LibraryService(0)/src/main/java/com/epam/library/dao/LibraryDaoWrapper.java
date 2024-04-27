package com.epam.library.dao;

import com.epam.library.bean.LibraryBean;
import com.epam.library.entity.Library;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class LibraryDaoWrapper {
    @Autowired
    LibraryDao libraryDao;

    public Library save(Library library) {
        return libraryDao.save(library);
    }

    public Library deleteByUsernameAndBookId(String username, int bookId) {
        Library library = libraryDao.findByUsernameAndBookId(username, bookId);
        if (library == null)
            return null;
        libraryDao.deleteByUsernameAndBookId(username,bookId);
        return library;
    }

    private Library convertBeanToEntity(LibraryBean libraryBean){
        Library library = new Library();
        BeanUtils.copyProperties(libraryBean, library);
        return library;
    }
}

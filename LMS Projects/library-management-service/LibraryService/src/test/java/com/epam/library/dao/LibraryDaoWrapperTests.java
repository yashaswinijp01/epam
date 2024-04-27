package com.epam.library.dao;

import com.epam.library.entity.Library;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;


@ExtendWith(MockitoExtension.class)
class LibraryDaoWrapperTests {

    @InjectMocks
    LibraryDaoWrapper libraryDaoWrapper;

    @Mock
    LibraryDao libraryDao;


    @Test
    void save() {
        Library library = new Library(1, "user", 1);
        Mockito.when(libraryDao.save(Mockito.any())).thenReturn(library);
        Library library1 = libraryDaoWrapper.save(new Library());
        Assertions.assertEquals(library.getBookId(), library1.getBookId());
        Assertions.assertEquals(library.getUsername(), library1.getUsername());
    }

    @Test
    void deleteByUsernameAndBookId() {
        Library library = new Library(1, "user", 1);
        Mockito.when(libraryDao.findByUsernameAndBookId(Mockito.anyString(), Mockito.anyInt())).thenReturn(library);
        Mockito.doNothing().when(libraryDao).deleteByUsernameAndBookId(Mockito.anyString(), Mockito.anyInt());
        Library library1 = libraryDaoWrapper.deleteByUsernameAndBookId("", 1);
        Assertions.assertEquals(library.getBookId(), library1.getBookId());
        Assertions.assertEquals(library.getUsername(), library1.getUsername());
        Mockito.verify(libraryDao, Mockito.times(1)).findByUsernameAndBookId("", 1);
    }
}
package com.epam.library;

import com.epam.library.dao.LibraryDao;
import com.epam.library.dao.LibraryDaoWrapper;
import com.epam.library.entity.Library;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith({MockitoExtension.class})
public class daoTest {

    @InjectMocks
    LibraryDaoWrapper libraryDaoWrapper;

    @Mock
    LibraryDao libraryDao;

    @Test
    void save() {
        Library library=new Library(1,"public",2);
        Mockito.when(libraryDao.save(Mockito.any())).thenReturn(library);
        Library library1=libraryDaoWrapper.save(new Library());
        Assertions.assertEquals(library1.getUsername(),"public");
        Assertions.assertEquals(library1.getBookId(),2);
        Assertions.assertEquals(library1.getId(),1);

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

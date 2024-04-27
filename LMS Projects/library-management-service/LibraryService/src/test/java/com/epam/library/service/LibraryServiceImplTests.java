package com.epam.library.service;

import com.epam.library.dao.LibraryDaoWrapper;
import com.epam.library.entity.Library;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class LibraryServiceImplTests {
    @InjectMocks
    LibraryServiceImpl libraryService;

    @Mock
    LibraryDaoWrapper libraryDaoWrapper;

    @Test
    void save() {
        Library library = new Library(1,"user",1);
        Mockito.when(libraryDaoWrapper.save(Mockito.any())).thenReturn(library);
        Library library1 = libraryService.save(new Library());
        Assertions.assertEquals(library.getBookId(), library1.getBookId());
        Assertions.assertEquals(library.getUsername(), library1.getUsername());
    }

    @Test
    void deleteByUsernameAndBookId() {
        Library library = new Library(1,"user",1);
        Mockito.when(libraryDaoWrapper.deleteByUsernameAndBookId(Mockito.anyString(), Mockito.anyInt())).thenReturn(library);
        Library library1 = libraryService.deleteByUsernameAndBookId("", 1);
        Assertions.assertEquals(library.getBookId(), library1.getBookId());
        Assertions.assertEquals(library.getUsername(), library1.getUsername());
    }
}
package com.epam.library;


import com.epam.library.dao.LibraryDaoWrapper;
import com.epam.library.entity.Library;
import com.epam.library.service.LibraryServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith({MockitoExtension.class})
public class serviceTest {

    @InjectMocks
    LibraryServiceImpl libraryService;

    @Mock
    LibraryDaoWrapper libraryDaoWrapper;

    @Test
    void addLibrary() {
        Library library = new Library(5, "python", 2);
        Mockito.when(libraryDaoWrapper.save(Mockito.any())).thenReturn(library);
        Library savedBook = libraryService.save(new Library());
        Assertions.assertEquals(5, savedBook.getId());
        Assertions.assertEquals(2, savedBook.getBookId());
        Assertions.assertEquals("python", savedBook.getUsername());
    }

    @Test
    void deleteByUsernameAndBookId(){
        Library library = new Library(5, "python", 2);
        libraryService.deleteByUsernameAndBookId("python",5);
        verify(libraryDaoWrapper,times(1)).deleteByUsernameAndBookId("python",5);

    }

    @Test
    void deleteBookWhenIdAndUserNameNotAvailable() {
        Mockito.when(libraryDaoWrapper.deleteByUsernameAndBookId(Mockito.any(),Mockito.anyInt())).thenReturn(null);
        Library deletedbook =libraryService.deleteByUsernameAndBookId("python",5);
        Assertions.assertNull(deletedbook);
    }

}

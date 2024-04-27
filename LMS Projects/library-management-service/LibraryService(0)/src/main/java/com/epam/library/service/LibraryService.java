package com.epam.library.service;

import com.epam.library.entity.Library;

public interface LibraryService {
    Library save(Library library);
    Library deleteByUsernameAndBookId(String username, int bookId);
}
package com.andrew.dao.jdbc.dao;

import com.andrew.dao.jdbc.domain.Author;

public interface AuthorDao {

    Author getById(Long id);

    Author findAuthorByName(String firstName, String lastName);

    Author saveNewAuthor(Author author);

    Author updateAuthor(Author saved);

    void deleteAuthor(Long id);
}

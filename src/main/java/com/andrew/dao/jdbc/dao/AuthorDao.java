package com.andrew.dao.jdbc.dao;

import com.andrew.dao.jdbc.domain.Author;

public interface AuthorDao {

    Author getById(Long id);

    Author findAuthorByName(String firstName, String lastName);

}

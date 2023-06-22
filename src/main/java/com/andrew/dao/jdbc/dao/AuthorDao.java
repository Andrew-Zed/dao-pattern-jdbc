package com.andrew.dao.jdbc.dao;

import com.andrew.dao.jdbc.domain.Author;

import java.sql.SQLException;

public interface AuthorDao {

    Author getById(Long id);

    Author findAuthorByName(String firstName, String lastName) throws SQLException;
}

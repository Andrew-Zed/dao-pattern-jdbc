package com.andrew.dao.jdbc.dao;

import com.andrew.dao.jdbc.domain.Author;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.*;

/**
 * Crested by Andrew-Zed on 20/6/2023
 */
@Component
public class AuthorDaoImpl implements AuthorDao {

    private final DataSource dataSource;

    public AuthorDaoImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    Connection connection = null;
    PreparedStatement ps = null;
    ResultSet resultSet = null;

    @Override
    public Author getById(Long id) {

        try {
            connection = dataSource.getConnection();
            ps = connection.prepareStatement("SELECT * FROM author where id = ?");
            ps.setLong(1, id);
            resultSet = ps.executeQuery();

            if (resultSet.next()) {

                return getAuthorFromRS(resultSet);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                closeAll(resultSet, ps, connection);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    @Override
    public Author findAuthorByName(String firstName, String lastName) {

        try {
            connection = dataSource.getConnection();
            ps = connection.prepareStatement("SELECT * FROM author where first_name = ? and last_name = ?");
            ps.setString(1, firstName);
            ps.setString(2, lastName);
            resultSet = ps.executeQuery();

            if (resultSet.next()) {

                return getAuthorFromRS(resultSet);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
            closeAll(resultSet, ps, connection);

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    @Override
    public Author saveNewAuthor(Author author) {

        try {
            connection = dataSource.getConnection();
            ps = connection.prepareStatement("INSERT INTO author (first_name, last_name) " + "VALUES (?, ?)");
            ps.setString(1, author.getFirstName());
            ps.setString(2, author.getLastName());
            ps.executeUpdate();

            Statement statement = connection.createStatement();

//            resultSet = statement.executeQuery("SELECT LAST_INSERT_ID()"); // This method only works for mysql (checkout the PostgresSQL equivalent)

            resultSet = statement.executeQuery("SELECT max(id) from author");

            if (resultSet.next()) {
                Long savedId = resultSet.getLong(1);

                return this.getById(savedId);
            }
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                closeAll(resultSet, ps, connection);

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    @Override
    public Author updateAuthor(Author author) {

        try {
            connection = dataSource.getConnection();
            ps = connection.prepareStatement("UPDATE author set first_name = ?,  last_name = ? where author.id = ?");
            ps.setString(1, author.getFirstName());
            ps.setString(2, author.getLastName());
            ps.setLong(3, author.getId());
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                closeAll(resultSet, ps, connection);

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return this.getById(author.getId());
    }

    @Override
    public void deleteAuthor(Long id) {
        try {
            connection = dataSource.getConnection();
            ps = connection.prepareStatement("DELETE from author where id = ?");
            ps.setLong(1, id);
            ps.execute();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                closeAll(null, ps, connection);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private Author getAuthorFromRS(ResultSet resultSet) throws SQLException {
        Author author = new Author();
        author.setId(resultSet.getLong("id"));
        author.setFirstName(resultSet.getString("first_name"));
        author.setLastName(resultSet.getString("last_name"));
        return author;
    }

    private void closeAll(ResultSet resultSet, PreparedStatement ps, Connection connection) throws SQLException {
        if (resultSet != null) {
            resultSet.close();
        }

        if (ps != null) {
            ps.close();
        }

        if (connection != null) {
            connection.close();
        }
    }

}

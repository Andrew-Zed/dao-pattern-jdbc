package com.andrew.dao.jdbc;

import com.andrew.dao.jdbc.dao.AuthorDao;
import com.andrew.dao.jdbc.dao.AuthorDaoImpl;
import com.andrew.dao.jdbc.domain.Author;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


/**
 * Created by Andrew 20/06/2023
 */
@ActiveProfiles("dev")
@DataJpaTest
//@ComponentScan(basePackages = {"com.andrew.dao.jdbc.dao"})
@Import(AuthorDaoImpl.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class AuthorDaoIntegrationTest {

    @Autowired
    AuthorDao authorDao;

    @Test
    void testDeleteAuthor() {
        Author author = new Author();
        author.setFirstName("John");
        author.setLastName("M");

        Author saved = authorDao.saveNewAuthor(author);

        authorDao.deleteAuthor(saved.getId());

        Author deleted = authorDao.getById(saved.getId());

        assertThat(deleted).isNull();
    }

    @Test
    void testUpdateAuthor() {
        Author author = new Author();
        author.setFirstName("John");
        author.setLastName("M");

        Author saved = authorDao.saveNewAuthor(author);

        saved.setLastName("Mark");
        Author updated = authorDao.updateAuthor(saved);

        assertThat(updated.getLastName()).isEqualTo("Mark");
    }

    @Test
    void testSaveAuthor() {
        Author author = new Author();
        author.setFirstName("Andrew");
        author.setLastName("Jones");
        Author saved = authorDao.saveNewAuthor(author);

        assertThat(saved).isNotNull();
    }

    @Test
    void testGetAuthor() {
        Author author = authorDao.getById(1L);

        assertThat(author).isNotNull();
    }

    @Test
    void testGetAuthorByName() {
        Author author = authorDao.findAuthorByName("Brian", "Tracy");

        assertThat(author).isNotNull();
    }


}

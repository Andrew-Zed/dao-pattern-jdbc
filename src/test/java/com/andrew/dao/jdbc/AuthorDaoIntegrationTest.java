package com.andrew.dao.jdbc;

import com.andrew.dao.jdbc.dao.AuthorDao;
import com.andrew.dao.jdbc.dao.AuthorDaoImpl;
import com.andrew.dao.jdbc.domain.Author;
import org.assertj.core.api.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;   //ClassBasedNavigableIterableAssert.assertThat;


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

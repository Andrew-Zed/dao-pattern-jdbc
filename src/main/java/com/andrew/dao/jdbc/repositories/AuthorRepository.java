package com.andrew.dao.jdbc.repositories;

import com.andrew.dao.jdbc.domain.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {

}

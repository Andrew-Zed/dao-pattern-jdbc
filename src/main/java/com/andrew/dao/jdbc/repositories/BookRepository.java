package com.andrew.dao.jdbc.repositories;

import com.andrew.dao.jdbc.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}

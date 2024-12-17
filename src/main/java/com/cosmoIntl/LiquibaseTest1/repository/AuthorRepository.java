package com.cosmoIntl.LiquibaseTest1.repository;

import com.cosmoIntl.LiquibaseTest1.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
    Optional<Author> findAuthorById(Long id);
    Optional<Author> findAuthorByName(String name);
}

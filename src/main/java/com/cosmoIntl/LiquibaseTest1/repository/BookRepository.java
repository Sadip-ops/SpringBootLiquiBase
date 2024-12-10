package com.cosmoIntl.LiquibaseTest1.repository;

import com.cosmoIntl.LiquibaseTest1.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
}

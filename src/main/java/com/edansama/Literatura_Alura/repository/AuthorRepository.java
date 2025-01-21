package com.edansama.Literatura_Alura.repository;

import com.edansama.Literatura_Alura.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
    Optional<Author> findByName(String name);

    @Query("SELECT a FROM Author a LEFT JOIN FETCH a.books WHERE (a.deathYear IS NULL OR a.deathYear > :date) AND a.birthYear <= :date")
    List<Author> findAuthorsAliveInCertainYear(@Param("date") int date);
}

package com.edansama.Literatura_Alura.repository;

import com.edansama.Literatura_Alura.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    Optional<Book> findByTitleIgnoreCase(String title);

    @Query("SELECT b FROM Book b WHERE b.languages = :language")
    List<Book> findByLanguage(String language);
}
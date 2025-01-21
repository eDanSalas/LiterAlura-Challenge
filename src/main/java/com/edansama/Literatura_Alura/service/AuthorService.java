package com.edansama.Literatura_Alura.service;

import com.edansama.Literatura_Alura.model.Author;
import com.edansama.Literatura_Alura.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {
    @Autowired private AuthorRepository authorRepository;

    public List<Author> showAuthors() {
        return authorRepository.findAll();
    }

    public void createAuthor(Author author) {
        authorRepository.save(author);
    }

    public Optional<Author> getById(Long id) {
        return authorRepository.findById(id);
    }

    public Optional<Author> getByName(String name) {
        return authorRepository.findByName(name);
    }

    public void deleteAuthor(Long id) {
        authorRepository.deleteById(id);
    }

    public List<Author> searchAliveAuthors(int date) {
        return authorRepository.findAuthorsAliveInCertainYear(date);
    }
}

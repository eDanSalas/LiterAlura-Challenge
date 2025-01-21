package com.edansama.Literatura_Alura.service;

import com.edansama.Literatura_Alura.model.Book;
import com.edansama.Literatura_Alura.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    public List<Book> showBooks() {
        return bookRepository.findAll();
    }

    public void createBook(Book book) {
        bookRepository.save(book);
    }

    public Optional<Book> findBook(Long id) {
        return bookRepository.findById(id);
    }

    public Optional<Book> findBookByTitle(String title) {
        return bookRepository.findByTitleIgnoreCase(title);
    }

    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }

    public List<Book> listByLangauge(String language) {
        return bookRepository.findByLanguage(language);
    }

}

package com.edansama.Literatura_Alura.model;

import com.edansama.Literatura_Alura.dto.BookDTO;
import com.edansama.Literatura_Alura.dto.ResponseBooksDTO;
import jakarta.persistence.*;

@Entity
@Table(name = "libros")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String languages;
    private Integer downloadCount;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;

    public Book(){}

    public Book(BookDTO bookDTO) {
        this.title = bookDTO.getTitle();
        this.languages = bookDTO.getLanguages().getFirst();
        this.downloadCount = bookDTO.getDownloadCount();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLanguages() {
        return languages;
    }

    public void setLanguages(String languages) {
        this.languages = languages;
    }

    public Integer getDownloadCount() {
        return downloadCount;
    }

    public void setDownloadCount(Integer downloadCount) {
        this.downloadCount = downloadCount;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "Libro---->" +
                ", Titulo: '" + title + '\'' +
                ", Idiomas: '" + languages + '\'' +
                ", Cantidad de descargas: " + downloadCount +
                ", Autor: " + author;
    }
}

package com.edansama.Literatura_Alura.model;

import com.edansama.Literatura_Alura.dto.AuthorDTO;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "autores")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Integer birthYear;
    private Integer deathYear;

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Book> books;

    public Author() {}

    public Author(AuthorDTO authorDTO) {
        this.name = authorDTO.getName().split(",")[0];
        this.birthYear = authorDTO.getBirthYear();
        this.deathYear = authorDTO.getDeathYear();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(Integer birthYear) {
        this.birthYear = birthYear;
    }

    public Integer getDeathYear() {
        return deathYear;
    }

    public void setDeathYear(Integer deathYear) {
        this.deathYear = deathYear;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    @Override
    public String toString() {
        return "Autor---->" +
                ", Nombre: '" + name + '\'' +
                ", Año de nacimiento: " + birthYear +
                ", Año de muerte; " + deathYear +
                ", Libros: " + books;
    }
}

package com.edansama.Literatura_Alura.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BookDTO {
    private Long id;
    private String title;
    private List<AuthorDTO> authors;
    private List<String> languages;
    @JsonAlias("download_count") private Integer downloadCount;

    public  BookDTO() {}

    public BookDTO(Long id, String title, List<AuthorDTO> authors, List<String> languages, Integer downloadCount) {
        this.id = id;
        this.title = title;
        this.authors = authors;
        this.languages = languages;
        this.downloadCount = downloadCount;
    }

    public Integer getDownloadCount() {
        return downloadCount;
    }

    public void setDownloadCount(Integer downloadCount) {
        this.downloadCount = downloadCount;
    }

    public List<String> getLanguages() {
        return languages;
    }

    public void setLanguages(List<String> languages) {
        this.languages = languages;
    }

    public List<AuthorDTO> getAuthors() {
        return authors;
    }

    public void setAuthors(List<AuthorDTO> authors) {
        this.authors = authors;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}

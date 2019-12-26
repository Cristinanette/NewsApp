package com.example.newsapp;

public class Article {

    private String source;
    private String author;
    private String title;
    private String description;
    private String url;

    public Article(String source, String author, String title, String description, String url) {
        this.source = source;
        this.author = author;
        this.title = title;
        this.description = description;
        this.url = url;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}

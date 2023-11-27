package com.lucasengcomp.junit.blog.model;

import java.util.Objects;

public class Post {
    private Long id;
    private String title;
    private String content;
    private Editor autor;
    private String slug;
    private Earnings earnings;
    private boolean paid;
    private boolean published;

    public Post() {
    }

    public Post(String title, String content, Editor autor, boolean paid, boolean published) {
        this(null, title, content, autor, null, null, paid, published);
    }

    public Post(Long id, String title, String content, Editor autor, String slug, Earnings earnings, boolean paid, boolean published) {
        Objects.requireNonNull(title);
        Objects.requireNonNull(content);
        Objects.requireNonNull(autor);
        this.id = id;
        this.title = title;
        this.content = content;
        this.autor = autor;
        this.slug = slug;
        this.earnings = earnings;
        this.paid = paid;
        this.published = published;
    }

    public void publicar() {
        this.published = true;
    }

    public void marcarComoPago() {
        this.paid = true;
    }

    public void updateData(Post post) {
        Objects.requireNonNull(post);
        this.title = post.title;
        this.content = post.content;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Editor getAutor() {
        return autor;
    }

    public void setAutor(Editor autor) {
        this.autor = autor;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public Earnings getEarnings() {
        return earnings;
    }

    public void setEarnings(Earnings earnings) {
        this.earnings = earnings;
    }

    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }

    public boolean isPublished() {
        return published;
    }

    public void setPublished(boolean published) {
        this.published = published;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Post post = (Post) o;
        return Objects.equals(id, post.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

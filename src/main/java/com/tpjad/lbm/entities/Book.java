package com.tpjad.lbm.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;
@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "isbn", length = 50, nullable = false, unique = true)
    private String isbn;

    @Column(name = "name", length = 100, nullable = false)
    private String name;

    @Column(name = "serialName", length = 50, nullable = false)
    private String serialName;

    @Column(name = "description", length = 250, nullable = false)
    private String description;

    @Column(name = "favourite", nullable = false)
    private Boolean favourite;

    @ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.ALL, CascadeType.MERGE, CascadeType.REMOVE })
    @JoinTable(name = "books_authors", joinColumns = { @JoinColumn(name = "book_id") }, inverseJoinColumns = {
            @JoinColumn(name = "author_id") })
    private Set<Author> authors = new HashSet<Author>();

    @ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.ALL, CascadeType.MERGE })
    @JoinTable(name = "books_categories", joinColumns = { @JoinColumn(name = "book_id") }, inverseJoinColumns = {
            @JoinColumn(name = "category_id") })
    private Set<Category> categories = new HashSet<Category>();

    @ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.ALL, CascadeType.MERGE })
    @JoinTable(name = "books_publishers", joinColumns = { @JoinColumn(name = "book_id") }, inverseJoinColumns = {
            @JoinColumn(name = "publisher_id") })
    private Set<Publisher> publishers = new HashSet<Publisher>();

    public Book(String isbn, String name, String serialName, String description) {
        this.isbn = isbn;
        this.name = name;
        this.serialName = serialName;
        this.description = description;
        this.favourite = false;
    }

    public void addAuthors(Author author) {
        this.authors.add(author);
        author.getBooks().add(this);
    }

    public void removeAuthors(Author author) {
        this.authors.remove(author);
        author.getBooks().remove(this);
    }

    public void addFavourite() {
        this.favourite = true;
    }

    public void removeFavourite() {
        this.favourite = false;
    }

    public void addCategories(Category category) {
        this.categories.add(category);
        category.getBooks().add(this);
    }

    public void removeCategories(Category category) {
        this.categories.remove(category);
        category.getBooks().remove(this);
    }

    public void addPublishers(Publisher publisher) {
        this.publishers.add(publisher);
        publisher.getBooks().add(this);
    }

    public void removePublishers(Publisher publisher) {
        this.publishers.remove(publisher);
        publisher.getBooks().remove(this);
    }
}
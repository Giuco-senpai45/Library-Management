package com.tpjad.lbm.service;

import com.tpjad.lbm.entities.Book;

import java.util.List;

public interface BookService {
    public List<Book> findAllBooks();
    public List<Book> findAllFavouriteBooks();
    public List<Book> findAllNonFavouriteBooks();
    public void updateBookFavouriteStatus(Long bookId, boolean newFavouriteStatus);

    public List<Book> searchBooks(String keyword);

    public Book findBookById(Long id);

    public void createBook(Book book);

    public void updateBook(Book book);

    public void deleteBook(Long id);
}

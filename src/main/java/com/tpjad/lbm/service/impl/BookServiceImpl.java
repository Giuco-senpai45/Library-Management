package com.tpjad.lbm.service.impl;

import com.tpjad.lbm.entities.Book;
import com.tpjad.lbm.exceptions.NotFoundException;
import com.tpjad.lbm.repositories.BookRepository;
import com.tpjad.lbm.service.BookService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    @Override
    public List<Book> findAllBooks() {
        return bookRepository.findAll();
    }

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    @Override
    public List<Book> findAllFavouriteBooks() { return bookRepository.findAllByFavouriteIsTrue();}

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    @Override
    public List<Book> findAllNonFavouriteBooks() { return bookRepository.findAllByFavouriteIsFalse();}

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    @Override
    public List<Book> searchBooks(String keyword) {
        if (keyword != null) {
            return bookRepository.search(keyword);
        }
        return bookRepository.findAll();
    }

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    @Override
    public Book findBookById(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Book not found with ID %d", id)));
    }

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    @Override
    public void updateBookFavouriteStatus(Long bookId, boolean newFavouriteStatus) {
        int updatedRows = bookRepository.updateFavouriteById(newFavouriteStatus, bookId);

        if (updatedRows > 0) {
            System.out.println("Book updated successfully");
        } else {
            System.out.println("Book not found or update failed");
        }
    }

    @Override
    public void createBook(Book book) {
        book.removeFavourite();
        bookRepository.save(book);
    }

    @Override
    public void updateBook(Book book) {
        bookRepository.save(book);
    }

    @Override
    public void deleteBook(Long id) {
        final Book book = bookRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Book not found with ID %d", id)));

        bookRepository.deleteById(book.getId());
    }
}

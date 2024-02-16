package com.tpjad.lbm.repositories;

import com.tpjad.lbm.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    @Query("SELECT b FROM Book b WHERE b.name LIKE %?1%" + " OR b.isbn LIKE %?1%" + " OR b.serialName LIKE %?1%")
    public List<Book> search(String keyword);

    @Query("SELECT b FROM Book b WHERE b.favourite = true")
    List<Book> findAllByFavouriteIsTrue();

    @Query("SELECT b FROM Book b WHERE b.favourite = false")
    List<Book> findAllByFavouriteIsFalse();

    @Modifying
    @Transactional
    @Query("UPDATE Book b SET b.favourite = :newFavouriteStatus WHERE b.id = :bookId")
    int updateFavouriteById(boolean newFavouriteStatus, Long bookId);
}

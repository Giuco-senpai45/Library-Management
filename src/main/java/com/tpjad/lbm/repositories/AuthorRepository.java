package com.tpjad.lbm.repositories;

import com.tpjad.lbm.entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}

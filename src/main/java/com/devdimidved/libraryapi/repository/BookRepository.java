package com.devdimidved.libraryapi.repository;

import com.devdimidved.libraryapi.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Integer> {
}

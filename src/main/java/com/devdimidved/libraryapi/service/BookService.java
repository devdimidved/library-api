package com.devdimidved.libraryapi.service;

import com.devdimidved.libraryapi.model.dto.BookDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BookService {
    List<BookDTO> getAllBooks();
    Page<BookDTO> getBooksPaginated(Pageable pageable, String username);
    List<BookDTO> getBooksInActiveUserRents(String username);
}

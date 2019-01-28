package com.devdimidved.libraryapi.controller;

import com.devdimidved.libraryapi.model.dto.BookDTO;
import com.devdimidved.libraryapi.service.BookService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class BookController {

    @Value("${library.show-books.default-current-page:10}")
    private int defaultCurrentPage;

    @Value("${library.show-books.default-page-size:1}")
    private int defaultPageSize;

    private BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/books")
    @ResponseStatus(HttpStatus.OK)
    public Page<BookDTO> getAllBooksPaginated(@RequestParam("page") Optional<Integer> page,
                                              @RequestParam("size") Optional<Integer> size,
                                              Authentication authentication) {
        int currentPage = page.orElse(defaultCurrentPage);
        int pageSize = size.orElse(defaultPageSize);
        return bookService.getBooksPaginated(PageRequest.of(currentPage - 1, pageSize), authentication.getName());
    }
}

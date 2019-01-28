package com.devdimidved.libraryapi.controller;

import com.devdimidved.libraryapi.model.dto.BookDTO;
import com.devdimidved.libraryapi.model.dto.RentRequest;
import com.devdimidved.libraryapi.service.BookService;
import com.devdimidved.libraryapi.service.RentService;
import com.devdimidved.libraryapi.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class RentController {
    private RentService rentService;
    private BookService bookService;

    public RentController(RentService rentService, BookService bookService) {
        this.rentService = rentService;
        this.bookService = bookService;
    }

    @PostMapping("/rents")
    @ResponseStatus(HttpStatus.CREATED)
    public void createRent(@Valid @RequestBody RentRequest rentRequest, Authentication authentication) {
        rentService.createRent(authentication.getName(), rentRequest.getBookId(), rentRequest.getDurationMonth());
    }

    @GetMapping("/rents")
    public List<BookDTO> getBooksInActiveUserRents(Authentication authentication) {
        return bookService.getBooksInActiveUserRents(authentication.getName());
    }
}

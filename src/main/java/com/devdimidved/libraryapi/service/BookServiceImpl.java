package com.devdimidved.libraryapi.service;

import com.devdimidved.libraryapi.model.Rent;
import com.devdimidved.libraryapi.model.User;
import com.devdimidved.libraryapi.model.dto.BookDTO;
import com.devdimidved.libraryapi.model.dto.BookToBookDTOMapper;
import com.devdimidved.libraryapi.repository.BookRepository;
import com.devdimidved.libraryapi.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {
    private BookRepository bookRepository;
    private BookToBookDTOMapper bookDTOMapper;
    private UserRepository userRepository;

    public BookServiceImpl(BookRepository bookRepository,
                           BookToBookDTOMapper bookDTOMapper,
                           UserRepository userRepository) {
        this.bookRepository = bookRepository;
        this.bookDTOMapper = bookDTOMapper;
        this.userRepository = userRepository;
    }

    @Override
    public List<BookDTO> getAllBooks() {
        return bookRepository.findAll()
                .stream()
                .map(bookDTOMapper::bookToBookDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Page<BookDTO> getBooksPaginated(Pageable pageable, String username) {
        User user = userRepository.findByUsername(username);
        return bookRepository.findAll(pageable).map(book -> {
            BookDTO bookDTO = bookDTOMapper.bookToBookDTO(book);
            LocalDate currentUserExpiryDate = book.getRents()
                    .stream()
                    .filter(rent -> rent.isActive() && rent.getUser().getId().equals(user.getId()))
                    .map(Rent::getExpiryDate)
                    .findFirst()
                    .orElse(null);
            if (currentUserExpiryDate != null) {
                bookDTO.setDaysBeforeExpiration((int) ChronoUnit.DAYS.between(LocalDate.now(), currentUserExpiryDate));
            }
            return bookDTO;
        });
    }

    @Override
    public List<BookDTO> getBooksInActiveUserRents(String username) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            return Collections.emptyList();
        }
        return user.getRents().stream()
                .filter(Rent::isActive)
                .map(rent -> {
                    BookDTO bookDTO = bookDTOMapper.bookToBookDTO(rent.getBook());
                    bookDTO.setDaysBeforeExpiration((int) ChronoUnit.DAYS.between(LocalDate.now(), rent.getExpiryDate()));
                    return bookDTO;
                })
                .collect(Collectors.toList());
    }
}

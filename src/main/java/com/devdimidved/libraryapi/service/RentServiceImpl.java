package com.devdimidved.libraryapi.service;

import com.devdimidved.libraryapi.model.Book;
import com.devdimidved.libraryapi.model.Rent;
import com.devdimidved.libraryapi.model.User;
import com.devdimidved.libraryapi.repository.BookRepository;
import com.devdimidved.libraryapi.repository.RentRepository;
import com.devdimidved.libraryapi.repository.UserRepository;
import com.devdimidved.libraryapi.service.exception.BookNotFoundException;
import com.devdimidved.libraryapi.service.exception.DuplicateRentException;
import com.devdimidved.libraryapi.service.exception.NotEnoughMoneyException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;

@Slf4j
@Service
public class RentServiceImpl implements RentService {

    private RentRepository rentRepository;
    private UserRepository userRepository;
    private BookRepository bookRepository;

    public RentServiceImpl(RentRepository rentRepository, UserRepository userRepository, BookRepository bookRepository) {
        this.rentRepository = rentRepository;
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    @Transactional
    public void createRent(String username, int bookId, int monthDuration) {
        User user = userRepository.findByUsername(username);
        Book book = bookRepository
                .findById(bookId)
                .orElseThrow(() -> new BookNotFoundException("No such book with id " + bookId));

        if (isBookInActiveUserRents(user, book)) {
            throw new DuplicateRentException("Found active rent for book with id " + bookId);
        }

        BigDecimal rentPrice = book.getRentPrice().multiply(BigDecimal.valueOf(monthDuration));
        BigDecimal balanceAfterTransaction = user.getBalance().subtract(rentPrice);
        if (balanceAfterTransaction.compareTo(BigDecimal.ZERO) < 0) {
            throw new NotEnoughMoneyException("Not enough money to rent book with id " + bookId);
        }

        Rent rent = new Rent();
        rent.setUser(user);
        rent.setBook(book);
        rent.setAmount(rentPrice);
        rent.setCreationDate(LocalDate.now());
        rent.setExpiryDate(LocalDate.now().plusMonths(monthDuration));
        rent.setActive(true);

        Rent rentSaved = rentRepository.save(rent);

        user.addRent(rentSaved);
        user.setBalance(balanceAfterTransaction);
        userRepository.save(user);

        book.addRent(rentSaved);
        bookRepository.save(book);
    }

    @Override
    @Transactional
    @Scheduled(cron = "5 0 * * * ?")
    public void validateRents() {
        log.info("Start validating rents");
        int updatedRents = rentRepository.validateRents();
        log.info("{} rent(s) got expired and marked as non-active", updatedRents);
        log.info("Done validating rents");
    }

    private boolean isBookInActiveUserRents(User user, Book book) {
        return user.getRents().stream()
                .filter(Rent::isActive)
                .map(Rent::getBook)
                .anyMatch(rentedBook -> rentedBook.equals(book));
    }
}

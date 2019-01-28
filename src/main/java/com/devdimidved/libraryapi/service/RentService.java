package com.devdimidved.libraryapi.service;

public interface RentService {
    void createRent(String username, int bookId, int monthDuration);
    void validateRents();
}

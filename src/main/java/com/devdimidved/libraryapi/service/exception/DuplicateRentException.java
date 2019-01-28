package com.devdimidved.libraryapi.service.exception;

public class DuplicateRentException extends RuntimeException {
    public DuplicateRentException() {
    }

    public DuplicateRentException(String message) {
        super(message);
    }
}

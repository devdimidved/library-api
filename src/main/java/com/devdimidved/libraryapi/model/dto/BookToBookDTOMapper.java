package com.devdimidved.libraryapi.model.dto;

import com.devdimidved.libraryapi.model.Book;
import org.springframework.stereotype.Component;

@Component
public class BookToBookDTOMapper {

    public BookDTO bookToBookDTO(Book book) {
        if (book == null) {
            return null;
        }
        BookDTO bookDTO = new BookDTO();
        bookDTO.setId(book.getId());
        bookDTO.setTitle(book.getTitle());
        bookDTO.setAuthor(book.getAuthor());
        bookDTO.setIsbn(book.getIsbn());
        return bookDTO;
    }
}

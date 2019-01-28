package com.devdimidved.libraryapi.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.math.BigDecimal;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BookDTO {
    private Integer id;
    private String title;
    private String author;
    private String isbn;
    private BigDecimal rentPrice;
    private Integer daysBeforeExpiration;
}

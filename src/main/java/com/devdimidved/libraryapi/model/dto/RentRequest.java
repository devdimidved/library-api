package com.devdimidved.libraryapi.model.dto;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Positive;

@Data
public class RentRequest {
    @Positive
    private int bookId;
    @Positive
    @Max(12)
    private int durationMonth;
}

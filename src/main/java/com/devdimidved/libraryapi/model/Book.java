package com.devdimidved.libraryapi.model;


import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
@ToString(exclude = "rents")
@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    private String author;
    private String isbn;
    private BigDecimal rentPrice;
    @OneToMany(mappedBy = "book")
    private List<Rent> rents = new ArrayList<>();

    public void addRent(Rent rent) {
        rents.add(rent);
    }
}
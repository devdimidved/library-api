package com.devdimidved.libraryapi.model;

import lombok.Data;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
@ToString(exclude = {"password", "rents"})
@Entity
public class User {
    @Id
    private Integer id;
    private String lastName;
    private String firstName;
    @Column(unique = true)
    private String username;
    private String password;
    private BigDecimal balance;
    @OneToMany(mappedBy = "user")
    private List<Rent> rents = new ArrayList<>();

    public void addRent(Rent rent) {
        this.rents.add(rent);
    }
}

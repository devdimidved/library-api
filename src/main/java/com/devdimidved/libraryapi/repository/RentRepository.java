package com.devdimidved.libraryapi.repository;

import com.devdimidved.libraryapi.model.Rent;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface RentRepository extends CrudRepository<Rent, Integer> {
    @Modifying
    @Query("update Rent r set r.isActive = false where r.isActive = true and r.expiryDate < current_date")
    int validateRents();
}

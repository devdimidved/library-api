package com.devdimidved.libraryapi.repository;

import com.devdimidved.libraryapi.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {
    User findByUsername(String username);
}

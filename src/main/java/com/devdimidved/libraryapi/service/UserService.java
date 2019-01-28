package com.devdimidved.libraryapi.service;

import com.devdimidved.libraryapi.model.User;

public interface UserService {
    User findByUsername(String username);
}

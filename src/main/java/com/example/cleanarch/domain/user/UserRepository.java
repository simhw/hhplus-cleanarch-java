package com.example.cleanarch.domain.user;

import java.util.Optional;

public interface UserRepository {
    Optional<User> findById(Long id);
}

package com.endava.internship.mocking.repository;

import com.endava.internship.mocking.model.User;

import java.util.Optional;

public interface UserRepository {
    Optional<User> findById(Integer userId);
}

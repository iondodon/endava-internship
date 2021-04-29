package com.endava.internship.mocking.repository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.endava.internship.mocking.model.Status;
import com.endava.internship.mocking.model.User;

public class InMemUserRepository implements UserRepository {

    private List<User> userList;

    public InMemUserRepository() {
        userList = Arrays.asList(
            new User(1, "John", Status.ACTIVE),
            new User(2, "Maria", Status.ACTIVE),
            new User(3, "Peter", Status.INACTIVE),
            new User(4, "Anna", Status.ACTIVE),
            new User(5, "David", Status.INACTIVE));
    }

    @Override
    public Optional<User> findById(Integer userId) {
        if (userId == null) {
            throw new IllegalArgumentException("User id must not be null");
        }
        return userList.stream().filter(user -> userId.equals(user.getId())).findFirst();
    }
}

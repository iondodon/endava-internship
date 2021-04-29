package com.endava.internship.mocking.service;

import java.util.UUID;

import com.endava.internship.mocking.model.User;

public interface ValidationService {
    void validateAmount(Double amount);

    void validatePaymentId(UUID paymentId);

    void validateUserId(Integer userId);

    void validateUser(User user);

    void validateMessage (String message);
}

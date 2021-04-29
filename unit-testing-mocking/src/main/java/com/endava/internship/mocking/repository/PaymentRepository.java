package com.endava.internship.mocking.repository;

import com.endava.internship.mocking.model.Payment;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PaymentRepository {
    Optional<Payment> findById(UUID paymentId);

    List<Payment> findAll();

    Payment save(Payment payment);

    Payment editMessage(UUID paymentId, String message);
}

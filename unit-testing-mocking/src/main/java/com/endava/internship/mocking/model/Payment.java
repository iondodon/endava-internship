package com.endava.internship.mocking.model;

import java.util.Objects;
import java.util.UUID;

public class Payment {

    private final UUID paymentId;

    private final Integer userId;

    private final Double amount;

    private String message;

    public Payment(Integer userId, Double amount, String message) {
        this (UUID.randomUUID(), userId, amount, message);
    }

    private Payment(UUID paymentId, Integer userId, Double amount, String message) {
        this.paymentId = paymentId;
        this.userId = userId;
        this.amount = amount;
        this.message = message;
    }

    public static Payment copyOf(Payment originalPayment) {
        return new Payment(originalPayment.paymentId, originalPayment.userId, originalPayment.amount, originalPayment.message);
    }

    public UUID getPaymentId() {
        return paymentId;
    }

    public Integer getUserId() {
        return userId;
    }

    public Double getAmount() {
        return amount;
    }
    
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Payment payment = (Payment) o;
        return Objects.equals(paymentId, payment.paymentId) &&
            Objects.equals(userId, payment.userId) &&
            Objects.equals(amount, payment.amount);
    }

    @Override
    public String toString() {
        return "Payment{" +
                "paymentId=" + paymentId +
                ", userId=" + userId +
                ", amount=" + amount +
                ", message='" + message + '\'' +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(paymentId);
    }
}

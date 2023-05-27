package com.dsa.functions.chapter1;

public class Payment {

    public final CreditCard creditCard;
    public final double amount;

    public Payment(CreditCard creditCard, double amount) {
        this.creditCard = creditCard;
        this.amount = amount;
    }

    public Payment combine(Payment payment) {
        if (creditCard.equals(payment.creditCard)) {
            return new Payment(creditCard, amount + payment.amount);
        } else {
            throw new IllegalStateException("Cards don't match.");
        }
    }
}

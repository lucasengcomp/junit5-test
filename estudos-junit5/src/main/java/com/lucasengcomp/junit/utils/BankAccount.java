package com.lucasengcomp.junit.utils;

import java.math.BigDecimal;

public class BankAccount {

    private BigDecimal balance;

    public BankAccount(BigDecimal balance) {
        if (balance == null) {
            throw new IllegalArgumentException("Invalid balance!");
        }
        this.balance = balance;
    }

    public BigDecimal getBalance() {
        return balance;
    }
}

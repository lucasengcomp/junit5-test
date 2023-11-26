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

    public void withdraw(BigDecimal value) {
        if (value == null || value.compareTo(BigDecimal.ONE) <= 0) {
            throw new IllegalArgumentException("Invalid value");
        }
        if (this.balance.compareTo(value) <= 0) {
            throw new RuntimeException("Insufficient funds");
        }
        subtractBalance(value);
    }

    public void deposit(BigDecimal valor) {
        if (valor == null || valor.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("The value must be greater than zero");
        }
        addBalance(valor);
    }

    private void addBalance(BigDecimal value) {
        balance = balance.add(value);
    }

    private void subtractBalance(BigDecimal value) {
        this.balance = this.balance.subtract(value);
    }

    public BigDecimal balanceAccount() {
        return this.balance;
    }
}

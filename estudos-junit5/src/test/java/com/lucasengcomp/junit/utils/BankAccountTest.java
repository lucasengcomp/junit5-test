package com.lucasengcomp.junit.utils;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class BankAccountTest {

    @Test
    void shouldThrowExceptionWhenTheBalanceIsNull() {
        assertThrows(IllegalArgumentException.class, () -> new BankAccount(null));
    }

    @Test
    void shouldAssignTheBalanceToAccount() {
        BigDecimal testBalance = new BigDecimal("73.00");
        BankAccount account = new BankAccount(testBalance);
        assertEquals(testBalance, account.getBalance(), "Balance should be set correctly");
    }
}

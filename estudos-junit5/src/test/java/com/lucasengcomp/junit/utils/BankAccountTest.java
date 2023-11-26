package com.lucasengcomp.junit.utils;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class BankAccountTest {

    @Test
    void shouldGetBalance() {
        BankAccount account = new BankAccount(new BigDecimal("73.00"));
        assertEquals(new BigDecimal("73.00"), account.balanceAccount());
    }

    @Test
    void shouldThrowExceptionWhenTheDepositLessThanZeroAndDisplayMessage() {
        BankAccount account = new BankAccount(new BigDecimal("73.00"));
        IllegalArgumentException exception =
                assertThrows(IllegalArgumentException.class, () -> account.deposit(new BigDecimal("-10.00")));
        assertEquals("The value must be greater than zero", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenTheDepositLessThanZero() {
        BankAccount account = new BankAccount(new BigDecimal("73.00"));
        assertThrows(IllegalArgumentException.class, () -> account.deposit(new BigDecimal("-10.00")));
    }

    @Test
    void shouldThrowExceptionWhenTheDepositIsZero() {
        BankAccount account = new BankAccount(new BigDecimal("73.00"));
        assertThrows(IllegalArgumentException.class, () -> account.deposit(new BigDecimal("0.00")));
    }

    @Test
    void shouldThrowExceptionWhenTryDepositValueZero() {
        BankAccount account = new BankAccount(new BigDecimal("73.00"));
        account.deposit(new BigDecimal("100.00"));
        assertEquals(new BigDecimal("173.00"), account.balanceAccount());
    }

    @Test
    void shouldThrowExceptionWhenTheBalanceIsNull() {
        assertThrows(IllegalArgumentException.class, () -> new BankAccount(null));
    }

    @Test
    void shouldThrowExceptionWhenTheBalanceIsNullAndValidatedMessageException() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new BankAccount(null));
        assertEquals("Invalid balance!", exception.getMessage());
    }

    @Test
    void shouldAssignTheBalanceToAccount() {
        BigDecimal testBalance = new BigDecimal("73.00");
        BankAccount account = new BankAccount(testBalance);
        assertEquals(testBalance, account.balanceAccount(), "Balance should be set correctly");
    }

    @Test
    void shouldWithdrawFromTheAccount() {
        BankAccount account = new BankAccount(new BigDecimal("73.00"));
        account.withdraw(new BigDecimal("20.00"));
        assertEquals(new BigDecimal("53.00"), account.balanceAccount());
    }

    @Test
    void shouldThrowExceptionWhenValueAccountThanZero() {
        BankAccount account = new BankAccount(new BigDecimal("0.00"));
        assertThrows(RuntimeException.class, () -> account.withdraw(BigDecimal.TEN));
    }

    @Test
    void shouldThrowExceptionWhenValueAccountLessThanZero() {
        BankAccount account = new BankAccount(new BigDecimal("-0.01"));
        assertThrows(RuntimeException.class, () -> account.withdraw(BigDecimal.TEN));
    }

    @Test
    void shouldThrowExceptionWhenValueIsNull() {
        BankAccount account = new BankAccount(new BigDecimal("73.00"));
        assertThrows(IllegalArgumentException.class, () -> account.withdraw(null));
    }

    @Test
    void shouldThrowExceptionWhenValueIsZero() {
        BankAccount account = new BankAccount(new BigDecimal("73.00"));
        assertThrows(IllegalArgumentException.class, () -> account.withdraw(BigDecimal.ZERO));
    }

    @Test
    void shouldThrowExceptionWhenValueIsLessThenZero() {
        BankAccount account = new BankAccount(new BigDecimal("73.00"));
        assertThrows(IllegalArgumentException.class, () -> account.withdraw(new BigDecimal(-10)));
    }
}

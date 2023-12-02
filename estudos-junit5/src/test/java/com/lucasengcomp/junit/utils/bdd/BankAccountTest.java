package com.lucasengcomp.junit.utils.bdd;

import com.lucasengcomp.junit.utils.BankAccount;
import org.junit.jupiter.api.*;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Bank account")
class BankAccountTest {

    @Nested
    @DisplayName("Given a bank account with a balance of R$10.00")
    class BankAccountWithBalance {
        private BankAccount account;

        @BeforeEach
        void beforeEach() {
            account = new BankAccount(BigDecimal.TEN);
        }

        @Nested
        @DisplayName("When withdrawing with a lower amount")
        class WithdrawSmallerAmount {
            private final BigDecimal valueBalance = new BigDecimal("9.00");

            @Test
            @DisplayName("So you shouldn't throw an exception")
            void shouldNotThrowException() {
                Assertions.assertDoesNotThrow(() -> account.withdraw(valueBalance));
            }

            @Test
            @DisplayName("Must subtract account balance")
            void mustSubtractThebalance() {
                account.withdraw(valueBalance);
                assertEquals(new BigDecimal("1.00"), account.balanceAccount());
            }
        }

        @Nested
        @DisplayName("When withdrawing with a larger amount")
        class WithdrawWithGreaterValue {
            private final BigDecimal valueWithdrawal = new BigDecimal("20.00");

            @Test
            @DisplayName("Must throw exception")
            void deveFalhar() {
                Assertions.assertThrows(RuntimeException.class, () -> account.withdraw(valueWithdrawal));
            }

            @Test
            @DisplayName("It should not allow changing the balance")
            void naoDeveAlterarSaldo() {
                try {
                    account.withdraw(valueWithdrawal);
                } catch (Exception ignored) {
                }
                assertEquals(BigDecimal.TEN, account.balanceAccount());
            }
        }
    }

    @Nested
    @DisplayName("Given a bank account with a balance of R$0.00")
    class BankAccountWithZeroBalance {
        private BankAccount account;

        @BeforeEach
        void beforeEach() {
            account = new BankAccount(BigDecimal.ZERO);
        }

        @Nested
        @DisplayName("When withdrawing with a larger amount")
        class WithdrawWithGreaterValue {
            private final BigDecimal valueWithdrawal = new BigDecimal("0.00");

            @Test
            @DisplayName("Must throw exception")
            void deveFalhar() {
                Assertions.assertThrows(RuntimeException.class, () -> account.withdraw(valueWithdrawal));
            }

            @Test
            @DisplayName("It should not allow changing the balance")
            void naoDeveAlterarSaldo() {
                try {
                    account.withdraw(valueWithdrawal);
                } catch (Exception ignored) {
                }
                assertEquals(BigDecimal.ZERO, account.balanceAccount());
            }
        }

        @Nested
        @DisplayName("When making a deposit of 8.00")
        class DepositWithValue10 {
            private final BigDecimal valueWithdrawal = new BigDecimal("8.00");

            @Test
            @DisplayName("Then it must be added to the balance")
            void thenAddedToTheBalance() {
                account.deposit(valueWithdrawal);
                assertEquals(new BigDecimal("8.00"), account.balanceAccount());
            }
        }
    }
}
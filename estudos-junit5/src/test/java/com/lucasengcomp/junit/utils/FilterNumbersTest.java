package com.lucasengcomp.junit.utils;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static com.lucasengcomp.junit.utils.FilterNumbers.*;
import static org.junit.jupiter.api.Assertions.*;

class FilterNumbersTest {

    @Test
    void mustCheckPairNumbers() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        List<Integer> pairNumbersList = Arrays.asList(2, 4, 6, 8, 10);
        List<Integer> resultFilter = pairNumbers(numbers);
        assertIterableEquals(pairNumbersList, resultFilter);
    }

    @Test
    void mustCheckOddNumbers() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        List<Integer> pairNumbersList = Arrays.asList(1, 3, 5, 7, 9);
        List<Integer> resultFilter = oddNumbers(numbers);
        assertIterableEquals(pairNumbersList, resultFilter);
    }

    @Test
    void musCheckPostiveNumber() {
        assertTrue(isPositive(5), "Should return true for positive number");
    }

    @Test
    void mustReturnFalseWhenTheNumberIsLessThanZero() {
        assertFalse(isPositive(-1), "Should return false for negative number");
    }
}

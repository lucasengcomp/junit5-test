package com.lucasengcomp.junit.utils;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static com.lucasengcomp.junit.utils.FilterNumbers.pairNumbers;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;

class FilterNumbersTest {

    @Test
    void mustCheckPairNumbers() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        List<Integer> pairNumbersList = Arrays.asList(2, 4, 6, 8, 10);
        List<Integer> resultFilter = pairNumbers(numbers);
        assertIterableEquals(pairNumbersList, resultFilter);
    }
}
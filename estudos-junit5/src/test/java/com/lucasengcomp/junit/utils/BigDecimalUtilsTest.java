package com.lucasengcomp.junit.utils;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BigDecimalUtilsTest {

    @ParameterizedTest
    @CsvSource({
            "10.00,10",
            "9.00,9.00"
    })
    void equals(BigDecimal value1, BigDecimal value2) {
        assertTrue(BigDecimalUtils.equals(value1, value2));
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/numbers.csv")
    void different(BigDecimal value1, BigDecimal value2) {
        assertFalse(BigDecimalUtils.equals(value1, value2));
    }
}

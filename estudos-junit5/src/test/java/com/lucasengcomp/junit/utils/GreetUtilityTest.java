package com.lucasengcomp.junit.utils;

import org.junit.jupiter.api.Test;

import static com.lucasengcomp.junit.utils.GreetUtility.greet;
import static org.junit.jupiter.api.Assertions.*;


class GreetUtilityTest {

    @Test
    void mustCheckMessageGoodMorning() {
        String greeting = greet(9);
        assertEquals("Good morning", greeting);
    }

    @Test
    void shouldThrowException() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> greet(-10));
        assertEquals("Invalid hour!", exception.getMessage());
    }

    @Test
    void shouldNotThrowException() {
        assertDoesNotThrow(() -> greet(10));
    }
}

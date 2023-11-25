package com.lucasengcomp.junit.utils;

import org.junit.jupiter.api.Test;

import static com.lucasengcomp.junit.utils.GreetUtility.greet;
import static org.junit.jupiter.api.Assertions.*;


class GreetUtilityTest {

    @Test
    void mustCheckMessageGoodMorning() {
        assertEquals("Good morning", greet(0));
        assertEquals("Good morning", greet(11));
    }

    @Test
    void mustCheckMessageGoodAfternoon() {
        assertEquals("Good afternoon", greet(12));
        assertEquals("Good afternoon", greet(17));
    }

    @Test
    void mustCheckMessageGoodnight() {
        assertEquals("Good night", greet(18));
        assertEquals("Good night", greet(23));
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

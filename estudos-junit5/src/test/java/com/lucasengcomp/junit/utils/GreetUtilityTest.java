package com.lucasengcomp.junit.utils;

import com.lucasengcomp.junit.CustomDisplayNameGenerator;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static com.lucasengcomp.junit.utils.GreetUtility.greet;
import static com.lucasengcomp.junit.utils.MessagesUtil.*;
import static org.junit.jupiter.api.Assertions.*;

@DisplayNameGeneration(CustomDisplayNameGenerator.class)
class GreetUtilityTest {

    @Test
    void mustCheckMessageGoodMorning() {
        assertEquals(GOOD_M0RNING, greet(11));
    }

    @Test
    void mustCheckMessageGoodDawn() {
        assertEquals(GOOD_DAWN, greet(4));
    }

    @Test
    void mustCheckMessageGoodAfternoon() {
        assertEquals(GOOD_AFTERNOOM, greet(12));
        assertEquals(GOOD_AFTERNOOM, greet(17));
    }

    @Test
    void mustCheckMessageGoodnight() {
        assertEquals(GOOD_NIGHT, greet(18));
        assertEquals(GOOD_NIGHT, greet(23));
    }

    @Test
    void shouldThrowException() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> greet(-10));
        assertEquals(INVALID_HOUR, exception.getMessage());
    }

    @Test
    void shouldNotThrowException() {
        assertDoesNotThrow(() -> greet(10));
    }

    @ParameterizedTest
    @ValueSource(ints = {5, 6, 7, 8, 9, 10, 11})
    void givenTimeMorningSoMustReturnGoodMorning(int hour) {
        assertEquals(GOOD_M0RNING, greet(hour));
    }

    @ParameterizedTest
    @ValueSource(ints = {12, 13, 14, 15, 16, 17})
    void givenTimeMorningSoMustReturnGoodAfternoom(int hour) {
        assertEquals(GOOD_AFTERNOOM, greet(hour));
    }

    @ParameterizedTest
    @ValueSource(ints = {18, 19, 20, 21, 22, 23})
    void givenTimeMorningSoMustReturnGoodNight(int hour) {
        assertEquals(GOOD_NIGHT, greet(hour));
    }

    @ParameterizedTest
    @ValueSource(ints = {0,1,2,3,4})
    void givenTimeMorningSoMustReturnGoodDawn(int hour) {
        assertEquals(GOOD_DAWN, greet(hour));
    }
}

package com.lucasengcomp.junit.utils.aaa;

import com.lucasengcomp.junit.CustomDisplayNameGenerator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.ThrowingSupplier;

import static com.lucasengcomp.junit.utils.GreetUtility.greet;
import static org.junit.jupiter.api.Assertions.*;


@DisplayNameGeneration(CustomDisplayNameGenerator.class)
class GreetUtilityAAATest {

    @Test
    void shouldThrowException() {
        //Arrange
        int invalidHour = -10;
        //Action
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> greet(invalidHour));
        //Assert
        assertEquals("Invalid hour!", exception.getMessage());
    }

    @Test
    void shouldNotThrowException() {
        //Arrange
        int validHour = 0;

        //Action
        ThrowingSupplier<String> validMethodCall = () -> greet(validHour);

        //Assert
        assertDoesNotThrow(validMethodCall);
    }
}

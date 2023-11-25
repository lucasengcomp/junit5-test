package com.lucasengcomp.junit.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class GreetUtilityTest {

    @Test
    void mustCheckMessageGoodMorning() {
        String greeting = GreetUtility.greet(9);
        assertEquals("Good morning", greeting);
    }
}

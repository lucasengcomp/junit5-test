package com.lucasengcomp.junit.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PeopleTest {


    @Test
    void groupedAssertion() {
        People people = new People("Lucas", "Galvao");

        assertAll("Assertions people",
                () -> assertEquals("Lucas", people.getName()),
                () -> assertEquals("Galvao", people.getLastName()));
    }
}

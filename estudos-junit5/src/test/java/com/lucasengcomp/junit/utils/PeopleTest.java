package com.lucasengcomp.junit.utils;

import com.lucasengcomp.junit.CustomDisplayNameGenerator;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayNameGeneration(CustomDisplayNameGenerator.class)
class PeopleTest {

    @Test
    @EnabledIfEnvironmentVariable(named = "ENV", matches = "PROD")
    void groupedAssertion() {
//        Assumptions.assumeTrue("PROD".equals(System.getenv("ENV")), () -> "Aborting test... Should not be run in a PROD environment");
        People people = new People("Lucas", "Galvao");

        assertAll("Assertions people",
                () -> assertEquals("Lucas", people.getName()),
                () -> assertEquals("Galvao", people.getLastName()));
    }
}

package com.lucasengcomp.junit.utils;

import com.lucasengcomp.junit.CustomDisplayNameGenerator;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

@DisplayNameGeneration(CustomDisplayNameGenerator.class)
class SimulatorWaitTest {

    @Test
    @Disabled("Test disabled")
    void mustWaitTimeOut() {
        assertTimeout(Duration.ofSeconds(1), ()-> SimulatorWait.wait(Duration.ofMillis(10)));
    }

    @Test
    @Disabled("Test disabled")
    void doesWaitTimeOut() {
        assertTimeoutPreemptively(Duration.ofSeconds(1), ()-> SimulatorWait.wait(Duration.ofMillis(10)));
    }
}
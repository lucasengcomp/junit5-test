package com.lucasengcomp.junit.utils;

import org.junit.jupiter.api.Test;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

class SimulatorWaitTest {

    @Test
    void mustWaitTimeOut() {
        assertTimeout(Duration.ofSeconds(1), ()-> SimulatorWait.wait(Duration.ofMillis(10)));
    }

    @Test
    void doesWaitTimeOut() {
        assertTimeoutPreemptively(Duration.ofSeconds(1), ()-> SimulatorWait.wait(Duration.ofMillis(10)));
    }
}
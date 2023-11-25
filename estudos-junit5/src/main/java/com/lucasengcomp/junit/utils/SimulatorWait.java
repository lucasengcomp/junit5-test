package com.lucasengcomp.junit.utils;

import java.time.Duration;

public class SimulatorWait {

    private SimulatorWait() {
    }

    public static void wait(Duration duration) throws Exception {
        try {
            Thread.sleep(duration.toMillis());
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
    }
}

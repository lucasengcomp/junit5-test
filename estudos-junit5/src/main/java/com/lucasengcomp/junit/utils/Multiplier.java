package com.lucasengcomp.junit.utils;

public enum Multiplier {

    MULTI_DOUBLE(2.0),
    MULTI_TRIPLE(3.0),
    MULTI_QUADRUPLE(4.0);

    private final double multiplierNumber;

    Multiplier(double multiplierNumber) {
        this.multiplierNumber = multiplierNumber;
    }

    Double apllyMultiplier(Double value) {
        if (value == null || value == 0) {
            return 0.0;
        }
        return multiplierNumber * value;
    }
}

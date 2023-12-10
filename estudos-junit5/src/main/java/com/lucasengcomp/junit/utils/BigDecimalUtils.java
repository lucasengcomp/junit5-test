package com.lucasengcomp.junit.utils;

import java.math.BigDecimal;

public class BigDecimalUtils {

    public static boolean equals(BigDecimal value1, BigDecimal value2) {
        return value1.compareTo(value2) == 0;
    }
}

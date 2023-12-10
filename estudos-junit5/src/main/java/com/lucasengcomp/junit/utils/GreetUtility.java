package com.lucasengcomp.junit.utils;

import static com.lucasengcomp.junit.utils.MessagesUtil.*;

public class GreetUtility {

    private GreetUtility() {
    }

    public static String greet(int hour) {
        if (!isValidHour(hour)) {
            throw new IllegalArgumentException(INVALID_HOUR);
        }
        return getGreetingForHour(hour);
    }

    private static boolean isValidHour(int hour) {
        return hour >= 0 && hour <= 23;
    }

    private static String getGreetingForHour(int hour) {
        if (hour <= 4) {
            return GOOD_DAWN;
        }
        if (hour <= 11) {
            return GOOD_M0RNING;
        }
        if (hour <= 17) {
            return GOOD_AFTERNOOM;
        }
        return GOOD_NIGHT;
    }
}

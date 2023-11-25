package com.lucasengcomp.junit.utils;

public class GreetUtility {

    private GreetUtility() {
    }

    public static String greet(int hour) {
        if (!isValidHour(hour)) {
            throw new IllegalArgumentException("Invalid hour!");
        }
        return getGreetingForHour(hour);
    }

    private static boolean isValidHour(int hour) {
        return hour >= 0 && hour <= 23;
    }

    private static String getGreetingForHour(int hour) {
        if (hour <= 11) {
            return "Good morning";
        }
        if (hour <= 17) {
            return "Good afternoon";
        }
        return "Good night";
    }
}

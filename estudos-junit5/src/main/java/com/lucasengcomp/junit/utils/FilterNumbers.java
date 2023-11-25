package com.lucasengcomp.junit.utils;

import java.util.ArrayList;
import java.util.List;

public class FilterNumbers {

    private FilterNumbers() {
    }

    public static List<Integer> pairNumbers(List<Integer> numbers) {
        return new ArrayList<>(numbers).stream().filter(number -> number % 2 == 0).toList();
    }

    public static List<Integer> oddNumbers(List<Integer> numbers) {
        return new ArrayList<>(numbers).stream().filter(number -> number % 2 != 0).toList();
    }

    public static boolean isPositive(int number) {
        return number >= 0;
    }
}

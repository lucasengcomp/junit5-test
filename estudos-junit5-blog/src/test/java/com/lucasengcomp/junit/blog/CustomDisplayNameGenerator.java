package com.lucasengcomp.junit.blog;

import org.junit.jupiter.api.DisplayNameGenerator;

import java.lang.reflect.Method;

public class CustomDisplayNameGenerator implements DisplayNameGenerator {

    @Override
    public String generateDisplayNameForClass(Class<?> testClass) {
        return formatCase(testClass.getSimpleName());
    }

    @Override
    public String generateDisplayNameForNestedClass(Class<?> nestedClass) {
        return nestedClass.getSimpleName();
    }

    @Override
    public String generateDisplayNameForMethod(Class<?> testClass, Method testMethod) {
        return formatCase(testMethod.getName());
    }

    private String formatCase(String input) {
        StringBuilder words = new StringBuilder();
        int start = 0;

        for (int i = 1; i < input.length(); i++) {
            start = verifyInitialCharacter(input, i, words, start);
        }

        words.append(input.substring(start));
        return formattedStringToLowerCase(words.toString().trim()) + ".";
    }

    private static String formattedStringToLowerCase(String formattedString) {
        if (!formattedString.isEmpty()) {
            formattedString = Character.toUpperCase(formattedString.charAt(0))
                    + formattedString.substring(1).toLowerCase();
        }
        return formattedString;
    }

    private static int verifyInitialCharacter(String input, int i, StringBuilder words, int start) {
        if (Character.isUpperCase(input.charAt(i))) {
            words.append(input, start, i).append(" ");
            start = i;
        }
        return start;
    }
}

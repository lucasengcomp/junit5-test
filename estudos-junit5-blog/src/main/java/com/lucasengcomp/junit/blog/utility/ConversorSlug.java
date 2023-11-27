package com.lucasengcomp.junit.blog.utility;

import java.text.Normalizer;
import java.util.Locale;
import java.util.regex.Pattern;

import static com.lucasengcomp.junit.blog.utility.CodeGenerator.generate;

public class ConversorSlug {

    private static final Pattern NONLATIN = Pattern.compile("[^\\w-]");
    private static final Pattern WHITESPACE = Pattern.compile("[\\s]");

    private ConversorSlug() {
    }

    public static String convertWithCode(String text) {
        return converter(text) + "-" + generate();
    }

    public static String converter(String text) {
        if (text == null) {
            throw new IllegalArgumentException("String null");
        }

        if (text.isEmpty()) {
            throw new IllegalArgumentException("String empty");
        }

        String nowhitespace = WHITESPACE.matcher(text).replaceAll("-");
        String normalized = Normalizer.normalize(nowhitespace, Normalizer.Form.NFD);
        String slug = NONLATIN.matcher(normalized).replaceAll("");

        return slug.toLowerCase(Locale.ENGLISH);
    }
}

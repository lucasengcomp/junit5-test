package com.lucasengcomp.junit.blog.utility;

import com.lucasengcomp.junit.blog.business.TextProcessor;

public class TextProcessorSimple implements TextProcessor {

    @Override
    public int quantityWords(String text) {
        if (text == null || text.isBlank()) {
            return 0;
        }
        return text.split(" ").length;
    }
}

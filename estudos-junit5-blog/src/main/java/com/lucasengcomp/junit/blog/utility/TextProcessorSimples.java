package com.lucasengcomp.junit.blog.utility;

import com.lucasengcomp.junit.blog.business.TextProcessor;

public class TextProcessorSimples implements TextProcessor {
    @Override
    public int quantityWords(String texto) {
        if (texto == null || texto.isBlank()) {
            return 0;
        }
        return texto.split(" ").length;
    }
}

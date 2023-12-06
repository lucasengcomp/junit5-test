package com.lucasengcomp.junit.blog.business;

import com.lucasengcomp.junit.blog.model.Editor;

import java.math.BigDecimal;

public class EditorFactoryObjects {

    public static Editor editorIdNull() {
        return new Editor(null, "Lucas", "lucas@email.com", BigDecimal.TEN, true);
    }

    public static Editor editorWithExistentId() {
        return new Editor(1L, "Lucas", "lucas@email.com", BigDecimal.TEN, true);
    }

    public static Editor editorWithInexistentId() {
        return new Editor(999L, "Lucas", "lucas@email.com", BigDecimal.TEN, true);
    }
}

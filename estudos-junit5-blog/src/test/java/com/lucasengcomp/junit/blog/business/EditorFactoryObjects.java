package com.lucasengcomp.junit.blog.business;

import com.lucasengcomp.junit.blog.model.Editor;

import java.math.BigDecimal;

public class EditorFactoryObjects {

    public static Editor.Builder editorIdNull() {
        return Editor.builder()
                .withName("Lucas")
                .withEmail("lucas@email.com")
                .withPaymentValueWithWord(BigDecimal.TEN)
                .withPremium(true);
    }

    public static Editor.Builder editorWithExistentId() {
        return editorIdNull().withId(1L);
    }

    public static Editor.Builder editorWithInexistentId() {
        return editorIdNull().withId(999L);
    }
}

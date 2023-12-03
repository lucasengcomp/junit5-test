package com.lucasengcomp.junit.blog.business;

import com.lucasengcomp.junit.blog.CustomDisplayNameGenerator;
import com.lucasengcomp.junit.blog.model.Editor;
import com.lucasengcomp.junit.blog.storage.StorageEditor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@DisplayNameGeneration(CustomDisplayNameGenerator.class)
class RegisterEditorWithMockTest {

    EditorRegistration editorRegistration;
    Editor editor;

    @BeforeEach
    void setUp() {
        editor = new Editor(null, "Lucas", "lucas@email.com", BigDecimal.TEN, true);

        StorageEditor storageEditor = mock(StorageEditor.class);
        when(storageEditor.save(editor))
                .thenReturn(new Editor(1L, "Lucas", "lucas@email.com", BigDecimal.TEN, true));
        EmailSendingManager emailSendingManager = mock(EmailSendingManager.class);

        editorRegistration = new EditorRegistration(storageEditor, emailSendingManager);
    }

    @Test
    void givenAnEditorValidWhenCreateThenMustReturnARegistrationId() {
        Editor editorSaved = editorRegistration.create(editor);
        assertEquals(1L, editorSaved.getId());
    }
}

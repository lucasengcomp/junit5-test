package com.lucasengcomp.junit.blog.business;

import com.lucasengcomp.junit.blog.CustomDisplayNameGenerator;
import com.lucasengcomp.junit.blog.model.Editor;
import com.lucasengcomp.junit.blog.storage.StorageEditor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayNameGeneration(CustomDisplayNameGenerator.class)
class RegisterEditorWithMockTest {

    Editor editor;

    @Mock
    StorageEditor storageEditor;

    @Mock
    EmailSendingManager emailSendingManager;

    @InjectMocks
    EditorRegistration editorRegistration;


    @BeforeEach
    void setUp() {
        editor = new Editor(null, "Lucas", "lucas@email.com", BigDecimal.TEN, true);

        when(storageEditor.save(editor))
                .thenReturn(new Editor(1L, "Lucas", "lucas@email.com", BigDecimal.TEN, true));
        EmailSendingManager emailSendingManager = mock(EmailSendingManager.class);
    }

    @Test
    void givenAnEditorValidWhenCreateThenMustReturnARegistrationId() {
        Editor editorSaved = editorRegistration.create(editor);
        assertEquals(1L, editorSaved.getId());
    }
}

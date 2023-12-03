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
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

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

        when(storageEditor.save(any(Editor.class)))
                .thenAnswer(invocation -> {
                    Editor editorParam = invocation.getArgument(0, Editor.class);
                    editorParam.setId(1L);
                    return editorParam;
                });
    }

    @Test
    void givenAnEditorValidWhenCreateThenMustReturnARegistrationId() {
        Editor editorSaved = editorRegistration.create(editor);
        assertEquals(1L, editorSaved.getId());
    }

    @Test
    void GivenAValidEditorWhenCreateShouldCallSaveFromStorage() {
        storageEditor.save(editor);
        verify(storageEditor, times(1)).save(eq(editor));
    }
}

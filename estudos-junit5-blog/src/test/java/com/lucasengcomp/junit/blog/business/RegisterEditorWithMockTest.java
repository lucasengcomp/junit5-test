package com.lucasengcomp.junit.blog.business;

import com.lucasengcomp.junit.blog.CustomDisplayNameGenerator;
import com.lucasengcomp.junit.blog.model.Editor;
import com.lucasengcomp.junit.blog.storage.StorageEditor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayNameGeneration(CustomDisplayNameGenerator.class)
class RegisterEditorWithMockTest {

    Editor editor;

    @Captor
    ArgumentCaptor<Message> messageArgumentCaptor;

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

    @Test
    void givenAValidEditorWhenCreateShouldCallSaveFromStorage() {
        storageEditor.save(editor);
        verify(storageEditor, times(1)).save(eq(editor));
    }

    @Test
    void givenAnEditorValidWhenCreateAndLaunchExceptionWhenSavingThenMustNotSendEmail() {
        when(storageEditor.save(editor)).thenThrow(new RuntimeException());
        assertAll("Email sending should not be allowed",
                () -> assertThrows(RuntimeException.class, () -> editorRegistration.create(editor)),
                () -> verify(emailSendingManager, never()).sendEmail(any())
        );
    }

    @Test
    void givenAnEditorValidWhenRegisterThenMustSendEmailWithDestinationToEditor() {
        messageArgumentCaptor = ArgumentCaptor.forClass(Message.class);
        Editor savedEditor = editorRegistration.create(editor);
        verify(emailSendingManager).sendEmail(messageArgumentCaptor.capture());
        Message message = messageArgumentCaptor.getValue();
        assertEquals(savedEditor.getEmail(), message.getRecipient());
    }
}

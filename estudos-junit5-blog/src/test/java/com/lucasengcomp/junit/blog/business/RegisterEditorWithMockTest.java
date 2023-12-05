package com.lucasengcomp.junit.blog.business;

import com.lucasengcomp.junit.blog.CustomDisplayNameGenerator;
import com.lucasengcomp.junit.blog.exception.BusinessRuleException;
import com.lucasengcomp.junit.blog.model.Editor;
import com.lucasengcomp.junit.blog.storage.StorageEditor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayNameGeneration(CustomDisplayNameGenerator.class)
class RegisterEditorWithMockTest {

    @Spy
    Editor editor = new Editor(null, "Lucas", "lucas@email.com", BigDecimal.TEN, true);

    @Captor
    ArgumentCaptor<Message> messageArgumentCaptor;

    @Mock
    StorageEditor storageEditor;

    @Mock
    EmailSendingManager emailSendingManager;

    @InjectMocks
    EditorRegistration editorRegistration;

    @Nested
    class RegistrationWithEditorValid {

        @BeforeEach
        void setUp() {
            when(storageEditor.save(editor))
                    .thenReturn(new Editor(1L, "Lucas", "lucas@email.com", BigDecimal.TEN, true));
            mock(EmailSendingManager.class);
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

        @Test
        void givenAnEditorValidWhenRegisterThenMustVerifyEmail() {
            editorRegistration.create(editor);
            verify(editor, atLeast(1)).getEmail();
        }

        @Test
        void givenAnEditorWithExistingEmailWhenRegisterThenMustThrowException() {
            when(storageEditor.findByEmail("lucas@email.com"))
                    .thenReturn(Optional.empty())
                    .thenReturn(Optional.of(editor));

            Editor newEditorWithEmail = new Editor(null, "Lucas", "lucas@email.com", BigDecimal.TEN, true);
            editorRegistration.create(editor);
            assertThrows(BusinessRuleException.class, () -> editorRegistration.create(newEditorWithEmail));
        }

        @Test
        void givenAnEditorValidWhenRegisterThenMustSendEmailAfterSave() {
            editorRegistration.create(editor);
            InOrder inOrder = inOrder(storageEditor, emailSendingManager);
            inOrder.verify(storageEditor, times(1)).save(editor);
            inOrder.verify(emailSendingManager, times(1)).sendEmail(any(Message.class));
        }
    }

    @Nested
    class RegistrationWithEditorNull {

        @Test
        void givenUmEditorNullWhenCadastrarEntaoDeveLancarException() {
            assertThrows(NullPointerException.class, () -> editorRegistration.create(null));
            verify(storageEditor, never()).save(any());
            verify(emailSendingManager, never()).sendEmail(any());
        }
    }
}

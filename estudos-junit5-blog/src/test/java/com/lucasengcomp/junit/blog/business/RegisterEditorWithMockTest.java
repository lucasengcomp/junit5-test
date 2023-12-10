package com.lucasengcomp.junit.blog.business;

import com.lucasengcomp.junit.blog.CustomDisplayNameGenerator;
import com.lucasengcomp.junit.blog.exception.BusinessRuleException;
import com.lucasengcomp.junit.blog.exception.EditorNotFoundException;
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

import static com.lucasengcomp.junit.blog.business.EditorFactoryObjects.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayNameGeneration(CustomDisplayNameGenerator.class)
class RegisterEditorWithMockTest {

    @Spy
    Editor editor = editorIdNull().build();

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
                    .thenReturn(editorWithExistentId().build());
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

            Editor newEditorWithEmail = editorIdNull().build();
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
        void givenUmEditorNullWhenRegisterThenMustThrowsNullPointerException() {
            assertThrows(NullPointerException.class, () -> editorRegistration.create(null));
            verify(storageEditor, never()).save(any());
            verify(emailSendingManager, never()).sendEmail(any());
        }
    }

    @Nested
    class EditionWithValidEditor {
        @Spy
        Editor editor = editorWithExistentId().build();

        @BeforeEach
        void setUp() {
            when(storageEditor.save(editor)).thenAnswer(invocation -> invocation.getArgument(0, Editor.class));
            when(storageEditor.findById(1L)).thenReturn(Optional.of(editor));
        }

        @Test
        void givenAnEditorValidWhenEditThenMustChangeSavedEditor() {
            Editor editorUpdated = editorWithExistentId()
                    .withEmail("lucas@email.com")
                    .withName("LUcas").build();
            editorRegistration.edit(editorUpdated);
            verify(editor, times(1)).updateWithData(editorUpdated);
            InOrder inOrder = inOrder(editor, storageEditor);
            inOrder.verify(editor).updateWithData(editorUpdated);
            inOrder.verify(storageEditor).save(editor);
        }
    }

    @Nested
    class EditionWithNonExistentEditor {

        Editor editor = editorWithInexistentId().build();

        @BeforeEach
        void setUp() {
            when(storageEditor.findById(999L)).thenReturn(Optional.empty());
        }

        @Test
        void givenAnEditorThatDoesExistWhenEditingThenShouldNotThrowEditorNotFoundException() {
            assertThrows(EditorNotFoundException.class, () -> editorRegistration.edit(editor));
            verify(storageEditor, never()).save(any(Editor.class));
        }
    }
}

package com.lucasengcomp.junit.blog.business;

import com.lucasengcomp.junit.blog.storage.StorageEditor;
import com.lucasengcomp.junit.blog.exception.EditorNotFoundException;
import com.lucasengcomp.junit.blog.exception.BusinessRuleException;
import com.lucasengcomp.junit.blog.model.Editor;

import java.util.Objects;

public class EditorRegistration {

    private final StorageEditor storageEditor;
    private final EmailSendingManager emailSendingManager;

    public EditorRegistration(StorageEditor storageEditor, EmailSendingManager emailSendingManager) {
        this.storageEditor = storageEditor;
        this.emailSendingManager = emailSendingManager;
    }

    public Editor create(Editor editor) {
        Objects.requireNonNull(editor);

        verifyIfEditorUsingSameEmail(editor);
        editor = storageEditor.save(editor);
        sendRegistrationEmail(editor);

        return editor;
    }

    public Editor edit(Editor updatedEditor) {
        Objects.requireNonNull(updatedEditor);

        checkIfEditorExistsUsingSameEmailWithDifferentId(updatedEditor);
        Editor editor = findEditor(updatedEditor);
        editor.updateWithData(updatedEditor);

        return storageEditor.save(editor);
    }

    public void remove(Long editorId) {
        Objects.requireNonNull(editorId);
        storageEditor.findById(editorId).orElseThrow(EditorNotFoundException::new);
        storageEditor.remove(editorId);
    }

    private void verifyIfEditorUsingSameEmail(Editor editor) {
        if(storageEditor.findByEmail(editor.getEmail()).isPresent()) {
            throw new BusinessRuleException("An editor with this email already exists " + editor.getEmail());
        }
    }

    private Editor findEditor(Editor editorAtualizado) {
        return storageEditor.findById(editorAtualizado.getId())
                .orElseThrow(EditorNotFoundException::new);
    }

    private void checkIfEditorExistsUsingSameEmailWithDifferentId(Editor editorAtualizado) {
        if(storageEditor.findByEmailDifferentId(
                editorAtualizado.getEmail(),
                editorAtualizado.getId()).isPresent()) {
            throw new BusinessRuleException("An editor with this email already exists " + editorAtualizado.getEmail());
        }
    }

    private void sendRegistrationEmail(Editor editor) {
        Message message = new Message(editor.getEmail(), "New registration", "Your registration has been completed");
        emailSendingManager.sendEmail(message);
    }
}

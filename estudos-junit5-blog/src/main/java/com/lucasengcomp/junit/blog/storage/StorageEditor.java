package com.lucasengcomp.junit.blog.storage;

import com.lucasengcomp.junit.blog.model.Editor;

import java.util.List;
import java.util.Optional;


public interface StorageEditor {
    Editor save(Editor editor);
    Optional<Editor> findById(Long editor);
    Optional<Editor> findByEmail(String email);
    Optional<Editor> findByEmailDifferentId(String email, Long id);
    void remove(Long editorId);
    List<Editor> findAll();
}

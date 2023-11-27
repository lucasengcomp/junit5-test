package com.lucasengcomp.junit.blog.storage;

import com.lucasengcomp.junit.blog.model.Post;

import java.util.List;
import java.util.Optional;


public interface StoragePost {
    Post save(Post post);
    Optional<Post> findByEmail(Long post);
    void remove(Long postId);
    List<Post> findAll();
}

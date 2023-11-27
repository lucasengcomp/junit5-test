package com.lucasengcomp.junit.blog.negocio;

import com.lucasengcomp.junit.blog.storage.StoragePost;
import com.lucasengcomp.junit.blog.exception.PostNotFoundException;
import com.lucasengcomp.junit.blog.exception.BusinessRuleException;
import com.lucasengcomp.junit.blog.modelo.Notification;
import com.lucasengcomp.junit.blog.modelo.Post;
import com.lucasengcomp.junit.blog.utility.ConversorSlug;

import java.time.OffsetDateTime;
import java.util.Objects;

public class PostRegistration {

    private final StoragePost storagePost;
    private final EarningsCalculator earningsCalculator;
    private final NotificationManager notificationManager;

    public PostRegistration(StoragePost storagePost,
                            EarningsCalculator earningsCalculator,
                            NotificationManager notificationManager) {
        this.storagePost = storagePost;
        this.earningsCalculator = earningsCalculator;
        this.notificationManager = notificationManager;
    }

    public Post criar(Post post) {
        Objects.requireNonNull(post);
        post.setSlug(createSlug(post));
        post.setEarnings(this.earningsCalculator.calculate(post));
        post = storagePost.save(post);
        enviarNotificacao(post);
        return post;
    }

    public Post editar(Post postAtualizado) {
        Objects.requireNonNull(postAtualizado);

        Post post = this.storagePost.findByEmail(postAtualizado.getId())
                .orElseThrow(PostNotFoundException::new);
        post.updateData(post);

        if (!post.isPaid()) {
            post.setEarnings(this.earningsCalculator.calculate(post));
        }

        return storagePost.save(post);
    }

    public void remover(Long postId) {
        Objects.requireNonNull(postId);
        Post post = this.storagePost.findByEmail(postId)
                .orElseThrow(PostNotFoundException::new);
        if (post.isPublished()) {
            throw new BusinessRuleException("Can't a published post be removed");
        }
        if (post.isPaid()) {
            throw new BusinessRuleException("Can't a paid post be removed");
        }
        this.storagePost.remove(postId);
    }

    private String createSlug(Post post) {
        return ConversorSlug.convertWithCode(post.getTitle());
    }

    private void enviarNotificacao(Post post) {
        Notification notification = new Notification(
                OffsetDateTime.now(),
                "New post -> " + post.getTitle()
        );
        this.notificationManager.send(notification);
    }
}

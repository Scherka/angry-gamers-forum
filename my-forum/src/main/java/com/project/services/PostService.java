package com.project.services;

import com.project.entities.Post;
import com.project.entities.Thread;
import com.project.entities.User;
import com.project.repositories.PostRepository;
import com.project.repositories.ThreadRepository;
import com.project.repositories.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class PostService {
    @Autowired
    private final PostRepository postRepository;
    private final UserService userService;
    private final ThreadService threadService;

    public @NonNull Post getById(@NonNull Long postId){
        log.info("[{}] Найти пост: {}", Instant.now(), postId);
        return postRepository.findById(postId)
                .orElseThrow(() -> new EntityNotFoundException("[" + Instant.now() + "] Пост не найден"));
    }

    @Transactional
    public @NonNull Post savePost(@NonNull Post post){
        return postRepository.save(post);
    }

    public @NonNull Post createPost(@NonNull Post post) {
        log.info("[{}] Создать пост в треде {}", Instant.now(), post.getThread().getId());

        User author = userService.getUserById(post.getAuthor().getId());
        Thread thread = threadService.getById(post.getThread().getId());

        post.setAuthor(author);
        post.setThread(thread);
        post.setMdDateCreated(LocalDateTime.now());
        return savePost(post);
    }

    public @NonNull Post updatePost(@NonNull Long postId, @NonNull Post newData) {
        log.info("[{}] Обновить пост ID {}", Instant.now(), postId);

        Post post = getById(postId);

        Optional.ofNullable(newData.getText()).map(post::setText);

        return savePost(post);
    }

    public List<Post> getPostsByThread(@NonNull Long threadId) {
        Thread thread = threadService.getById(threadId);
        return postRepository.findByThread(thread);
    }

    @Transactional
    public void deletePost(Long postId) {
        log.info("[{}] Удалить пост ID {}", Instant.now(), postId);
        postRepository.deleteById(postId);
    }
}
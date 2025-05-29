package com.project.controllers;

import com.project.dto.PostDto;
import com.project.entities.Post;
import com.project.entities.Thread;
import com.project.entities.User;
import com.project.services.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping("/{id}")
    public ResponseEntity<PostDto> getPostById(@PathVariable Long id) {
        Post post = postService.getById(id);
        return ResponseEntity.ok(mapToDto(post));
    }

    @GetMapping("/thread/{threadId}")
    public ResponseEntity<List<PostDto>> getPostsByThread(@PathVariable Long threadId) {
        List<PostDto> postsDto = postService.getPostsByThread(threadId)
            .stream()
            .map(this::mapToDto)
            .collect(Collectors.toList());
        return ResponseEntity.ok(postsDto);
    }

    @PostMapping
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto) {
        Post post = mapToEntity(postDto);
        Post saved = postService.createPost(post);
        return ResponseEntity.ok(mapToDto(saved));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PostDto> updatePost(@PathVariable Long id, @RequestBody PostDto postDto) {
        Post post = mapToEntity(postDto);
        Post updated = postService.updatePost(id, post);
        return ResponseEntity.ok(mapToDto(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable Long id) {
        postService.deletePost(id);
        return ResponseEntity.noContent().build();
    }

    private PostDto mapToDto(Post post) {
        return new PostDto(
            post.getId(),
            post.getAuthor().getId(),
            post.getThread().getId(),
            post.getText(),
            post.getMdDateCreated()
        );
    }

    private Post mapToEntity(PostDto dto) {
        Post post = new Post();
        post.setId(dto.getId());
        post.setText(dto.getText());
        post.setMdDateCreated(dto.getMdDateCreated());
        
        // Создаем сущности User и Thread с id, чтобы избежать загрузки из базы (можно доработать)
        User author = new User();
        author.setId(dto.getAuthorId());
        post.setAuthor(author);

        Thread thread = new Thread();
        thread.setId(dto.getThreadId());
        post.setThread(thread);

        return post;
    }
}

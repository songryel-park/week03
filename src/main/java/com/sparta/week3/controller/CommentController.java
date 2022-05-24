package com.sparta.week3.controller;

import com.sparta.week3.domain.Comment;
import com.sparta.week3.domain.CommentRepository;
import com.sparta.week3.domain.CommentRequestDto;
import com.sparta.week3.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class CommentController {
    private final CommentRepository commentRepository;
    private final CommentService commentService;

    @PostMapping("/api/comments")
    public Comment createComment(@RequestBody CommentRequestDto requestDto) {
        Comment ment = new Comment(requestDto);
        return commentRepository.save(ment);
    }

    @GetMapping("/api/comments")
    public List<Comment> getComment() {
        return commentRepository.findAllByOrderByModifiedAtDesc();
    }

    @PutMapping("/api/comments/{id}")
    public Long updateComment(@PathVariable Long id, @RequestBody CommentRequestDto requestDto) {
        commentService.update(id, requestDto);
        return id;
    }

    @DeleteMapping("/api/comments/{id}")
    public Long deleteComment(@PathVariable Long id) {
        commentRepository.deleteById(id);
        return id;
    }
}

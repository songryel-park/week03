package com.sparta.week3.service;

import com.sparta.week3.domain.Comment;
import com.sparta.week3.domain.CommentRepository;
import com.sparta.week3.domain.CommentRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class CommentService {

    private final CommentRepository commentRepository;

    @Transactional
    public Long update(Long id, CommentRequestDto requestDto) {
        Comment ment = commentRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
        );
        if(ment.getPassword() == requestDto.getPassword()) {

        }
        ment.update(requestDto);
        return ment.getId();
    }

    @Transactional
    public Long delete(Long id, CommentRequestDto requestDto) {
        Comment ment = commentRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
        );
        if(ment.getPassword() == requestDto.getPassword()) {

        }
        ment.delete(requestDto);
        return ment.getId();
    }
}

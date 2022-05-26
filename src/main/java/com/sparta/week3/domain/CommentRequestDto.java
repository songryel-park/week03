package com.sparta.week3.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.RequiredArgsConstructor;

@Getter
@Setter
@RequiredArgsConstructor
public class CommentRequestDto {
    private String title;
    private String username;
    private String contents;
    private String password;
}

package com.sparta.week3.domain;

import lombok.Getter;

@Getter
public class CommentRequestDto {
    private String title;
    private String username;
    private String contents;
    private String password;
}

package com.sparta.week3.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class Comment {
    public Comment() {
    }

    public Comment(String comment) {
        this.comment = comment;
    }

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @ManyToOne(targetEntity = User.class,fetch = FetchType.LAZY)
    @JoinColumn
    private User user;

    @Column(nullable = false)
    private String comment;
}

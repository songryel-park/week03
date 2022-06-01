package com.sparta.week3.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
@Getter
@Setter
public class Notice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String newName;

    @ManyToOne(targetEntity = User.class,fetch = FetchType.LAZY)
    @JoinColumn
    private User user;

    @Column
    private String contents;

    @Column
    private String title;

    @OneToMany(targetEntity = Comment.class,fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<Comment> comments = new ArrayList<>();
    public List<Comment> getReverseComments() {
            List<Comment> copy = comments;
            Collections.reverse(copy);
            return copy;
    }

    public void addComment(Comment comments) {
        this.comments.add(comments);
    }
    public void removeComment(Comment comments) {
        this.comments.remove(comments);
    }
}

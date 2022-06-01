package com.sparta.week3.domain;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Entity
@ToString
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String nickName;

    @Column
    private String name;

    @Column(unique = true)
    private String loginId;

    @Column
    private String loginPw;

    public boolean build(){
        return (loginId.matches("^[a-zA-Z0-9]{3,}$") && !loginPw.contains(nickName) && loginPw.length() > 3);
    }
}

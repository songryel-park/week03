package com.sparta.week3.domain;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Getter
public class Detail {
    private final User user;
    public Detail(User user) {
        this.user = user;
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> auths = new ArrayList<>();
        return auths;
    }

    public String getPassword() {
        return user.getLoginPw();
    }

    public String getUsername() {
        return user.getLoginId();
    }

    public boolean isAccountNonExpired() {
        return true;
    }

    public boolean isAccountNonLocked() {
        return true;
    }

    public boolean isCredentialsNonExpired() {
        return true;
    }

    public boolean isEnabled() {
        return true;
    }

    public boolean isUser(long id) {
        return this.user.getId() == id;
    }
}

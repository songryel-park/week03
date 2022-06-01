package com.sparta.week3.service;

import com.sparta.week3.domain.User;
import com.sparta.week3.domain.Detail;
import com.sparta.week3.repository.UserRepository;
import com.sparta.week3.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class JwtAuthService {
    private final UserRepository repository;
    private final PasswordEncoder encoder;
    private final JwtTokenProvider provider;
    public String login(String username,String password) throws Exception {

        User u = repository.findByLoginId(username);
        if(u == null)
            return null;
        else if(!encoder.matches(password,u.getLoginPw())){
            return null;
        }
        Detail detail = new Detail(u);
        return provider.generateToken(detail);
    }
}

package com.sparta.week3.service;

import com.sparta.week3.domain.User;
import com.sparta.week3.repository.UserRepository;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Getter
public class UserService {
    @Autowired
    private UserRepository repository;

    @Autowired
    private BCryptPasswordEncoder encoder;

    public User save(User user){
        if(!user.build())
            throw new IllegalArgumentException("ID 혹은 비밀번호가 잘못됐습니다.");
        else
            user.setLoginPw(encoder.encode(user.getLoginPw()));
        return repository.save(user);
    }

    public boolean isUseableId(String id) {
        return repository.countByLoginId(id) == 0;
    }
}

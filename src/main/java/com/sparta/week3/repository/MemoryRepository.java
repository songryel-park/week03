package com.sparta.week3.repository;

import com.sparta.week3.domain.User;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Repository;

import java.util.*;

public class MemoryRepository {
    Map<Long, User> memory = new HashMap<>();
    private long ID_SEQUENCE = 1L;
    public User save(User u){
        if(!u.build()){
            throw new IllegalArgumentException("잘못된 ID거나, 닉네임이 포함되어있습니다");
        }else if(!isUseableId(u.getLoginId())){
            throw new DuplicateKeyException("존재하는 ID입니다");
        }else{
            System.out.println("사용이 가능합니다:" +ID_SEQUENCE);
            u.setId(ID_SEQUENCE);
            memory.put(ID_SEQUENCE++,u);
        }
        return u;
    }
    private boolean isUseableId(String id){
        return memory.values().stream().filter(e->e.getLoginId().equals(id)).count() == 0;
    }
}

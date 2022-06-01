package com.sparta.week3.repository;

import com.sparta.week3.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    @Query("Select this_ from User this_ where this_.loginId=:loginId")
    public User findByLoginId(@Param("loginId") String loginId);

    @Query("Select this_ from User this_ where this_.loginId=:loginId and this_.loginPw=:loginPw")
    public User findByLoginId(@Param("loginId") String loginId, @Param("loginPw") String loginPw);

    public int countByLoginId(String loginId);
}

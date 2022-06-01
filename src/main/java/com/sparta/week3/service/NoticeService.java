package com.sparta.week3.service;

import com.sparta.week3.domain.Comment;
import com.sparta.week3.domain.Detail;
import com.sparta.week3.domain.Notice;
import com.sparta.week3.repository.NoticeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class NoticeService {
    @Autowired
    private NoticeRepository repository;

    public Optional<Notice> getNoticeById(long id) {
        return repository.findById(id);
    }
    public Notice saveNotice(Notice notice) {
        return repository.save(notice);
    }

    public List<Notice> getNotice(){
        return repository.findAllByOrderByIdDesc();
    }

    public Notice addComment(long id, Comment comment){
        comment.setUser(((Detail) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUser());
        Notice notice = repository.findById(id).get();
        notice.addComment(comment);
        return repository.save(notice);
    }

    public void deleteNotice(Notice notice){
        repository.delete(notice);
    }

    public void deleteNotice(long id){
        repository.deleteById(id);
    }
}

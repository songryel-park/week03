package com.sparta.week3.service;

import com.sparta.week3.domain.Comment;
import com.sparta.week3.domain.Detail;
import com.sparta.week3.domain.Notice;
import com.sparta.week3.repository.CommentRepository;
import com.sparta.week3.repository.NoticeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class CommentService {
    @Autowired
    private CommentRepository crepository;

    @Autowired
    private NoticeRepository nrepository;

    @Transactional
    public void saveComment(long noticeid, String comment){
        Notice n = nrepository.findById(noticeid).get();
        Comment c = new Comment(comment);
        c.setUser(((Detail) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUser());
        n.addComment(c);
        nrepository.save(n);
    }

    public void updateComment(long id, String comment){
        Comment c = crepository.findById(id).get();
        c.setComment(comment);
        crepository.save(c);
    }

    public void deleteComment(long id){
        Notice n = nrepository.findByContentsId(id);
        Comment c = crepository.findById(id).get();
        n.removeComment(c);
        nrepository.save(n);
        crepository.delete(c);
    }
}

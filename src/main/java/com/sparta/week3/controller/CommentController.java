package com.sparta.week3.controller;

import com.sparta.week3.Util;
import com.sparta.week3.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequiredArgsConstructor
@RestController
public class CommentController {
    private final CommentService service;

    @PostMapping("/api/comments/{noticeid}")
    public RedirectView saveComment(@PathVariable("noticeid")String id, String comment, HttpServletResponse response) throws IOException {
        if(comment.length() > 0) {
            service.saveComment(Long.parseLong(id), comment);
        }else{
            Util.alert(response,"댓글을 입력해 주세요","/notice/"+id);
            return null;
        }
        return new RedirectView("/notice/"+id);
    }

    @PatchMapping("/api/comments/{id}")
    public String updateComment(@PathVariable("id")String id,String comment) {
        try {
            service.updateComment(Long.parseLong(id),comment);
            return "save";
        }catch (Exception e){
            return e.getMessage();
        }
    }

    @DeleteMapping("/api/comments/{id}")
    public String deleteComment(@PathVariable("id") String id){
        try {
            service.deleteComment(Long.parseLong(id));
            return "save";
        }catch (Exception e){
            return e.getMessage();
        }
    }
}

package com.sparta.week3.controller;

import com.sparta.week3.domain.Detail;
import com.sparta.week3.domain.Notice;
import com.sparta.week3.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

public class NoticeController {
    @Autowired
    private NoticeService service;


    @GetMapping(value = "/notice")
    public List<Notice> getAllNotice() {
        return service.getNotice();
    }

    @PostMapping(value="/notice")
    public RedirectView updateComment(Detail detail, Notice Notice) {
        Notice.setUser(detail.getUser());
        service.saveNotice(Notice);
        return new RedirectView("/main");
    }

    @PostMapping(value="/notice/{noticeId}")
    public RedirectView saveNotice(@PathVariable("noticeId") String noticeId, Notice Notice){
        service.patchNotice(Notice);
        return new RedirectView("/main");
    }

    @DeleteMapping(value = "/notice/{id}")
    public String deleteNotice(@PathVariable("id") String id){
        try {
            service.deleteNotice(Long.parseLong(id));
        }catch (Exception e){
            e.printStackTrace();
            return e.getMessage();
        }
        return "save";
    }
}

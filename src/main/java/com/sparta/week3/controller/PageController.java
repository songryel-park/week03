package com.sparta.week3.controller;

import com.sparta.week3.domain.Detail;
import com.sparta.week3.domain.Notice;
import com.sparta.week3.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class PageController {
    @GetMapping("/")
    public RedirectView redirectMain(){
        return new RedirectView("/main");
    }
    @Autowired
    private NoticeService service;
    @GetMapping(value = "/login")
    public String toLogin(){
        return "index";
    }
    @GetMapping(value = "/user/join")
    public String toJoin(){
        return "join";
    }
    @GetMapping(value = "/main")
    public String toMain(Model model){
        model.addAttribute("notices",service.getNotice());
        return "main";
    }

    @GetMapping(value="/newpage")
    public String toEdit(Model model){
        model.addAttribute("isUpdate",false);
        model.addAttribute("data",new Notice());
        return "editpage";
    }

    @GetMapping(value="/notice/{id}")
    public String commentInfo(@PathVariable("id") String id, Detail detail, Model model){
        Notice notice = service.getNoticeById(Long.parseLong(id)).get();
        model.addAttribute("data",notice);

        if(detail != null) {
            model.addAttribute("owner", notice.getUser().getId() == detail.getUser().getId());
        }else {
            model.addAttribute("owner", false);
        }
        return "notice";
    }
    @GetMapping(value="/editpage/{id}")
    public String updatePage(@PathVariable("id") String id, Model model){
        model.addAttribute("data",service.getNoticeById(Long.parseLong(id)).get());
        model.addAttribute("isUpdate",true);
        return "editpage";
    }
}

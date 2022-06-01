package com.sparta.week3.controller;

import com.sparta.week3.Value;
import com.sparta.week3.domain.User;
import com.sparta.week3.service.CookieService;
import com.sparta.week3.service.JwtAuthService;
import com.sparta.week3.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@RestController
@RequiredArgsConstructor
public class UserController {
    private UserService service;
    private JwtAuthService jservice;
    private CookieService cookie;

    @Autowired
    public UserController(UserService service, JwtAuthService jservice, CookieService cookie) {
        this.service = service;
        this.jservice = jservice;
        this.cookie = cookie;
    }

    @PostMapping(value = "/user/login")
    public String jwtLogin(String username, String password, HttpServletResponse response){
        try {
            String token = jservice.login(username,password);
            if(token!=null) {
                response.addCookie(cookie.createCookie(Value.JWT_TOKEN_COOKIE_KEY,token));
                return "success";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "fail";
    }
    @PostMapping(value = "/user")
    public Object joinUser(User user) throws UnsupportedEncodingException {
        String url = "/login";
        try{
            service.save(user);
        }catch (IllegalArgumentException e){
            url = "/user/join?msg="+ URLEncoder.encode("ID 또는 비밀번호가 잘못되었습니다.","UTF-8");
        }catch (DataIntegrityViolationException e){
            url = "/user/join?msg="+URLEncoder.encode("중복된 ID 입니다","UTF-8");
        }
        return new RedirectView(url);
    }
}

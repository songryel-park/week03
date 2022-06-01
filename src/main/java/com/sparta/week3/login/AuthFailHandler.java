package com.sparta.week3.login;

import com.sparta.week3.Util;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AuthFailHandler extends SimpleUrlAuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        exception.printStackTrace();
        String errorMsg;
        if(exception instanceof BadCredentialsException || exception instanceof UsernameNotFoundException){
            errorMsg = "ID 또는 비밀번호가 다릅니다";
        }else if(exception instanceof InternalAuthenticationServiceException){
            errorMsg = "인증되지 않은 ID입니다";
        }else if(exception instanceof CredentialsExpiredException){
            errorMsg = "세션이 만료되어 로그인이 필요합니다";
        }else{
            errorMsg = "알 수 없는 오류가 발생했습니다";
        }
        Util.alert(response,errorMsg,"/login");
    }
}

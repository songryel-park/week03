package com.sparta.week3.login;

import com.sparta.week3.security.JwtAuthFilter;
import com.sparta.week3.security.JwtTokenProvider;
import com.sparta.week3.service.CookieService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final UserAuthService authService;
    private final AuthFailHandler failhandler;
    private final CustomAccess denieHander;
    private final CookieService cookie;
    private final JwtTokenProvider provider;
    private final JwtAuthFilter filter;
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/h2-console/**");
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class); // JWT Token 검사로직
        http
                .authorizeRequests()
                .antMatchers("/user/**").permitAll()
                .antMatchers("/notice/**").permitAll()
                .antMatchers("/main").permitAll()
                .antMatchers("/login").permitAll()
                .antMatchers("/").permitAll()
                .mvcMatchers("/v2/**",
                        "/configuration/**",
                        "/swagger*/**",
                        "/webjars/**",
                        "/swagger-resources/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .logout()
                .logoutUrl("/user/logout").permitAll()
                .and()
                .exceptionHandling().authenticationEntryPoint(denieHander)
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    @Bean
    public BCryptPasswordEncoder getPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
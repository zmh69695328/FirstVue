package com.zmh.springbootdemo.controller;

import javax.servlet.http.HttpServletRequest;

import com.zmh.springbootdemo.service.JWTUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Test {
    @Autowired
    private NewsList tmp;
    @Autowired
    private HttpServletRequest request;

    @GetMapping("/test")
    @CrossOrigin
    public void test() {
        String JWT_TOKEN = request.getHeader("Authorization");
        String username = JWTUtil.getUsername(JWT_TOKEN);
        tmp.specialFieldQuery(username);
    }
}
package com.zmh.springbootdemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Test{
    @Autowired
    private NewsList tmp;

    @GetMapping("/test")
    @CrossOrigin
    public void test(){
        tmp.specialFieldQuery();
    }
}
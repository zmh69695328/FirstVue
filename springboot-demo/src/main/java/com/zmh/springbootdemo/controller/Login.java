package com.zmh.springbootdemo.controller;

import com.zmh.springbootdemo.domain.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Login {

    @PostMapping("/login")
    @CrossOrigin
    public void handle(@RequestParam(value = "username") String username,
            @RequestParam(value = "password") String password) {
        System.out.println("---------------------------");
        System.out.println(password + " ----------" + username);
    }
}
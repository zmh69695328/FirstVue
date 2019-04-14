package com.zmh.springbootdemo.controller;

import com.zmh.springbootdemo.domain.User;
import com.zmh.springbootdemo.service.MD5Util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Register {
    @Autowired
    private MongoTemplate mongoTemplate;

    @CrossOrigin
    @PostMapping("/register")
    public String handle(@RequestBody User user) {
        System.out.println("---------------------------");
        String avatarul = "https://s.gravatar.com/avatar/";
        avatarul += MD5Util.md5Hex(user.getEmail());
        avatarul += "?s=80";
        user.setAvatar(avatarul);
        user.setId(user.hashCode());
        System.out.println(user.toString());
        mongoTemplate.save(user, "User");
        return "ok";
    }
}
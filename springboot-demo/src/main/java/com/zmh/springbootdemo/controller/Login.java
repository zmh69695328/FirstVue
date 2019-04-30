package com.zmh.springbootdemo.controller;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.zmh.springbootdemo.domain.User;
import com.zmh.springbootdemo.service.JWTUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Login {

    @Autowired
    private MongoTemplate mongoTemplate;

    @CrossOrigin
    @PostMapping("/login")
    public String handle(@RequestBody User user) {
        Map<String, Object> res = new HashMap<>();
        String username = user.getUsername();
        Query query = new Query(Criteria.where("username").is(username));// 可累加条件
        User tmp = mongoTemplate.findOne(query, User.class, "User");
        System.out.println(tmp.toString());
        if (!tmp.getPassword().equals(user.getPassword())) {
            res.put("status", false);
            return JSON.toJSONString(res);
        }
        res.put("status", true);
        res.put("JWT_TOKEN", JWTUtil.sign(user.getUsername(), user.getPassword()));
        return JSON.toJSONString(res);
    }
}
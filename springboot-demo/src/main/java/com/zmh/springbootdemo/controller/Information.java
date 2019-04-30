package com.zmh.springbootdemo.controller;

import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSON;
import com.auth0.jwt.JWT;
import com.zmh.springbootdemo.domain.User;
import com.zmh.springbootdemo.service.JWTUtil;

import org.jdom2.internal.SystemProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Information {
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private MongoTemplate mongoTemplate;

    @CrossOrigin
    @GetMapping("/information")
    public String test() {
        String JWT_TOKEN = request.getHeader("Authorization");
        String username = JWTUtil.getUsername(JWT_TOKEN);

        Query query = new Query(Criteria.where("username").is(username));// 可累加条件
        User tmp = mongoTemplate.findOne(query, User.class, "User");

        return JSON.toJSONString(tmp);
    }

}

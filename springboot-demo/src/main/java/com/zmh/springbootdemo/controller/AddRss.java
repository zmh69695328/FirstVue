package com.zmh.springbootdemo.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSON;
import com.zmh.springbootdemo.domain.RSShub;
import com.zmh.springbootdemo.service.JWTUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AddRss {
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private MongoTemplate mongoTemplate;

    @GetMapping("/get/rss")
    @CrossOrigin
    public String test() {
        String JWT_TOKEN = request.getHeader("Authorization");
        String username = JWTUtil.getUsername(JWT_TOKEN);
        System.out.println("-------------------------------------");
        System.out.println(JWT_TOKEN + "\n" + username);
        Query query = new Query(Criteria.where("username").is(username));// 可累加条件
        List<RSShub> tmp = mongoTemplate.find(query, RSShub.class, "RSShub");
        tmp.add(new RSShub("澎湃新闻", "https://rsshub.app/thepaper/featured", username));
        System.out.println("rss  " + tmp.toString());
        return JSON.toJSONString(tmp);
    }

    @PostMapping("/post/rss")
    @CrossOrigin
    public String handle(@RequestBody RSShub rss) {
        String JWT_TOKEN = request.getHeader("Authorization");
        String username = JWTUtil.getUsername(JWT_TOKEN);
        rss.setUsername(username);
        mongoTemplate.save(rss, "RSShub");
        return JSON.toJSONString(rss);
    }

    @PostMapping("/delete/rss")
    @CrossOrigin
    public String handle2(@RequestBody RSShub rss) {
        String JWT_TOKEN = request.getHeader("Authorization");
        String username = JWTUtil.getUsername(JWT_TOKEN);
        rss.setUsername(username);
        Query query = new Query(Criteria.where("name").is(rss.getName()).and("username").is(username));
        mongoTemplate.remove(query, "RSShub");
        return JSON.toJSONString(rss);
    }

}
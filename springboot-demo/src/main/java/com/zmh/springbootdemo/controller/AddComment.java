package com.zmh.springbootdemo.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSON;
import com.zmh.springbootdemo.domain.Comment;

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
public class AddComment {
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private MongoTemplate mongoTemplate;

    @PostMapping("/get/comment")
    @CrossOrigin
    public String addComment(@RequestBody Map<String, Object> id) {
        System.out.println("-------Comment--------");
        System.out.println(id.get("id"));
        Query query = new Query(Criteria.where("newsid").is(id.get("id")));// 可累加条件
        List<Comment> tmp = mongoTemplate.find(query, Comment.class, "Comment");
        System.out.println(tmp.toString());
        return JSON.toJSONString(tmp);
    }

    @PostMapping("/post/comment")
    @CrossOrigin
    public void postComment(@RequestBody Comment comment) {
        System.out.println("------post-------");
        System.out.println(comment.toString());
        mongoTemplate.save(comment, "Comment");
    }
}
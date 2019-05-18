package com.zmh.springbootdemo.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSON;
import com.zmh.springbootdemo.domain.Favorite;
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
public class ChangeFavorite {
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private MongoTemplate mongoTemplate;

    @PostMapping("/delete/favorite")
    @CrossOrigin
    public void delete(@RequestBody Map<String, Object> id) {
        String JWT_TOKEN = request.getHeader("Authorization");
        String username = JWTUtil.getUsername(JWT_TOKEN);
        System.out.println("-------favorite--------");
        System.out.println(id.get("newsid"));
        Query query = new Query(Criteria.where("newsid").is(id.get("newsid")).and("userid").is(username));// 可累加条件
        mongoTemplate.remove(query, "Favorite");
    }

    @PostMapping("/add/favorite")
    @CrossOrigin
    public void add(@RequestBody Favorite favorite) {
        String JWT_TOKEN = request.getHeader("Authorization");
        String username = JWTUtil.getUsername(JWT_TOKEN);
        favorite.setUserid(username);

        System.out.println(favorite.toString());
        mongoTemplate.save(favorite, "Favorite");
    }

    @GetMapping("/get/favorite")
    @CrossOrigin
    public String get() {
        String JWT_TOKEN = request.getHeader("Authorization");
        String username = JWTUtil.getUsername(JWT_TOKEN);
        Query query = new Query(Criteria.where("userid").is(username));// 可累加条件
        List<Favorite> tmp = mongoTemplate.find(query, Favorite.class, "Favorite");
        System.out.println("------add-------");
        System.out.println(JSON.toJSONString(tmp) + "   " + username);
        System.out.println("-----end-----");
        return JSON.toJSONString(tmp);
    }
}

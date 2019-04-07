package com.zmh.springbootdemo.controller;

import java.awt.List;

import com.zmh.springbootdemo.dao.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.MongoTransactionException;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.VariableOperators.Map;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;

@Component
@RestController
public class NewsList {
    private static final String COLLECTION_NAME = "test";

    @Autowired
    private MongoTemplate mongoTemplate;
    public void specialFieldQuery() {
        System.out.println("-------------");
        Query query = new Query(Criteria.where("id").is(11));
        // 查询一条满足条件的数据

        Person result = mongoTemplate.findOne(query,Person.class,"test");
        System.out.println("query: " + query + " | specialFieldQueryOne: " + result);
        Person mgtest =new Person();
        mgtest.setId(11);
        mgtest.setAge(44);
        mgtest.setName("ceshi");
        mongoTemplate.save(mgtest,"test");
        
    }
}
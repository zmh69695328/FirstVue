package com.zmh.springbootdemo.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class Information {
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private MongoTemplate mongoTemplate;

    @GetMapping("/rss")
    @CrossOrigin
    public String test() {
        String JWT_TOKEN = request.getHeader("Authorization");
        String username = JWTUtil.getUsername(JWT_TOKEN);
        System.out.println("-------------------------------------");
        System.out.println(JWT_TOKEN + "\n" + username);
        Query query = new Query(Criteria.where("username").is(username));// 可累加条件
        User tmp = mongoTemplate.findOne(query, User.class, "RSShub");
        System.out.println(tmp.toString());
        return JSON.toJSONString(tmp);
    }

    @PostMapping("/rss")
    @CrossOrigin
    public String handle(@RequestBody RSShub rss) {

    }
}
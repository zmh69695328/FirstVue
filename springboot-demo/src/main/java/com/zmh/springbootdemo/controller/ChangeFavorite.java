package com.zmh.springbootdemo.controller;

@RestController
public class ChangeFavorite {
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private MongoTemplate mongoTemplate;

    @PostMapping("/delete/favorite")
    @CrossOrigin
    public String delete(@RequestBody Map<String, Object> id) {
        System.out.println("-------favorite--------");
        System.out.println(id.get("id"));
        Query query = new Query(Criteria.where("newsid").is(id.get("id")));// 可累加条件
        List<Comment> tmp = mongoTemplate.find(query, Comment.class, "Comment");
        System.out.println(tmp.toString());
        return JSON.toJSONString(tmp);
    }

    @PostMapping("/add/favorite")
    @CrossOrigin
    public void add(@RequestBody Comment comment) {
        System.out.println("------add-------");
        System.out.println(comment.toString());
        mongoTemplate.save(comment, "Comment");
    }
}

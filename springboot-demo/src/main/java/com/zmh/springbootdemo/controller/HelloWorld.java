package com.zmh.springbootdemo.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.alibaba.fastjson.JSON;
import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;
import com.zmh.springbootdemo.dao.RSSItemBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.util.SocketUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorld {
    private static final String COLLECTION_NAME = "rSSItemBean";

    @Autowired
    private MongoTemplate mongoTemplate;

    @GetMapping("/news")
    @CrossOrigin
    public String getNews() {
        List<RSSItemBean> rssList = mongoTemplate.findAll(RSSItemBean.class, COLLECTION_NAME);
        Collections.shuffle(rssList);
        String jsonOutput = JSON.toJSONString(rssList);
        return jsonOutput;
    }

    public List<RSSItemBean> getAllRssItemBeanList(String rssUrl) {
        try {
            System.setProperty("https.protocols", "TLSv1.2,TLSv1.1,SSLv3");
            URL feedUrl = new URL(rssUrl);
            SyndFeedInput input = new SyndFeedInput();
            SyndFeed feed = input.build(new XmlReader(feedUrl));

            List<SyndEntry> entries = feed.getEntries();

            RSSItemBean item = null;
            List<RSSItemBean> rssItemBeans = new ArrayList<RSSItemBean>();

            for (SyndEntry entry : entries) {
                item = new RSSItemBean();
                String tmp = entry.getDescription().getValue();
                int idx = tmp.indexOf("img src=");
                int st = 0, ed = 0;
                item.setDescription(tmp);
                if (idx == -1) {
                    item.setImg("");
                } else {
                    for (int i = idx, cnt = 0; i < tmp.length(); i++) {
                        if (tmp.charAt(i) == '"') {
                            if (cnt == 0) {
                                st = i;
                                cnt++;
                            } else if (cnt == 1) {
                                ed = i;
                                break;
                            }
                        }
                    }
                    item.setImg(tmp.substring(st + 1, ed));
                }

                item.setTitle(entry.getTitle().trim());
                item.setType(feed.getTitleEx().getValue().trim());
                item.setUri(entry.getUri());
                item.setPubDate(entry.getPublishedDate());
                item.setAuthor(entry.getAuthor());
                String regEx = "[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
                Pattern p = Pattern.compile(regEx);
                Matcher m = p.matcher(item.getDescription().substring(0, 50));
                item.setImg(m.toString());
                System.out.println("***---------\n" + item.getImg());
                rssItemBeans.add(item);
            }
            return rssItemBeans;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

}
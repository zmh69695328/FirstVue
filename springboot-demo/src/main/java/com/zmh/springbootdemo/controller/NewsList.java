package com.zmh.springbootdemo.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;
import com.zmh.springbootdemo.dao.RSSItemBean;
import com.zmh.springbootdemo.domain.RSShub;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;

@Component
@RestController
public class NewsList {
    private static final String collectionName = "rSSItemBean";

    @Autowired
    private MongoTemplate mongoTemplate;

    public void specialFieldQuery(String username) {
        String rssUrl = "https://rsshub.app/thepaper/featured";
        List<RSSItemBean> rssList = getAllRssItemBeanList(rssUrl);
        Query query = new Query(Criteria.where("username").is(username));// 可累加条件
        for (RSShub a : mongoTemplate.find(query, RSShub.class, "RSShub")) {
            // mongoTemplate.insertAll(rssList);
            rssList.addAll(getAllRssItemBeanList(a.getUrl()));
        }
        for (RSSItemBean tmp : rssList) {
            mongoTemplate.save(tmp, collectionName);
        }

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
                item.set_id(String.valueOf(item.hashCode()));
                String str = item.getDescription();
                String regex = "<[^>]+>";
                Pattern p = Pattern.compile(regex);
                Matcher m = p.matcher(str);
                System.out.println(m.replaceAll(""));
                item.setInfo(m.replaceAll(""));
                rssItemBeans.add(item);
            }
            return rssItemBeans;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }
}
package com.zmh.springbootdemo.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.Getter;
import lombok.Setter;

import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndEntryImpl;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;
import com.zmh.springbootdemo.dao.*;

@RestController
public class HelloWorld {
    @GetMapping("/hello")
    @CrossOrigin
    public String helloworld() {
        String rssUrl = "https://rsshub.app/thepaper/featured";
        System.out.println("1111");
        List<RSSItemBean> rssList = getAllRssItemBeanList(rssUrl);
        for (int i = 0; i < rssList.size(); i++) {
            System.out.println(rssList.get(i).getDescription());
            System.out.println("--------------------------------------------------------------------------");

        }

        String jsonOutput = JSON.toJSONString(rssList);
        // System.out.println("22 " + jsonOutput);

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

                item.setTitle(entry.getTitle().trim());
                item.setType(feed.getTitleEx().getValue().trim());
                item.setUri(entry.getUri());
                item.setPubDate(entry.getPublishedDate());
                item.setAuthor(entry.getAuthor());
                item.setDescription(entry.getDescription().getValue());
                rssItemBeans.add(item);

            }
            return rssItemBeans;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }
}
package com.zmh.springbootdemo.domain;

import java.util.Objects;

public class Comment {
    private String content;
    private String username;
    private String news;
    private String response;

    public Comment() {
    }

    public Comment(String content, String username, String news, String response) {
        this.content = content;
        this.username = username;
        this.news = news;
        this.response = response;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNews() {
        return this.news;
    }

    public void setNews(String news) {
        this.news = news;
    }

    public String getResponse() {
        return this.response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public Comment content(String content) {
        this.content = content;
        return this;
    }

    public Comment username(String username) {
        this.username = username;
        return this;
    }

    public Comment news(String news) {
        this.news = news;
        return this;
    }

    public Comment response(String response) {
        this.response = response;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Comment)) {
            return false;
        }
        Comment comment = (Comment) o;
        return Objects.equals(content, comment.content) && Objects.equals(username, comment.username)
                && Objects.equals(news, comment.news) && Objects.equals(response, comment.response);
    }

    @Override
    public int hashCode() {
        return Objects.hash(content, username, news, response);
    }

    @Override
    public String toString() {
        return "{" + " content='" + getContent() + "'" + ", username='" + getUsername() + "'" + ", news='" + getNews()
                + "'" + ", response='" + getResponse() + "'" + "}";
    }

}
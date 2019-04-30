package com.zmh.springbootdemo.domain;

import java.util.Objects;

public class Comment {
    private String content;
    private String username;
    private String newsid;// id
    private String avatar;
    private String datetime;

    public Comment() {
    }

    public Comment(String content, String username, String newsid, String avatar, String datetime) {
        this.content = content;
        this.username = username;
        this.newsid = newsid;
        this.avatar = avatar;
        this.datetime = datetime;
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

    public String getNewsid() {
        return this.newsid;
    }

    public void setNewsid(String newsid) {
        this.newsid = newsid;
    }

    public String getAvatar() {
        return this.avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getDatetime() {
        return this.datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public Comment content(String content) {
        this.content = content;
        return this;
    }

    public Comment username(String username) {
        this.username = username;
        return this;
    }

    public Comment newsid(String newsid) {
        this.newsid = newsid;
        return this;
    }

    public Comment avatar(String avatar) {
        this.avatar = avatar;
        return this;
    }

    public Comment datetime(String datetime) {
        this.datetime = datetime;
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
                && Objects.equals(newsid, comment.newsid) && Objects.equals(avatar, comment.avatar)
                && Objects.equals(datetime, comment.datetime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(content, username, newsid, avatar, datetime);
    }

    @Override
    public String toString() {
        return "{" + " content='" + getContent() + "'" + ", username='" + getUsername() + "'" + ", newsid='"
                + getNewsid() + "'" + ", avatar='" + getAvatar() + "'" + ", datetime='" + getDatetime() + "'" + "}";
    }

}
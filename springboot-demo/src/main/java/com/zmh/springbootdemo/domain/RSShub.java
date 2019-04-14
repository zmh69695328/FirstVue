package com.zmh.springbootdemo.domain;

import java.util.Objects;

public class RSShub {
    private String name;
    private String url;
    private String username;

    public RSShub() {
    }

    public RSShub(String name, String url, String username) {
        this.name = name;
        this.url = url;
        this.username = username;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public RSShub name(String name) {
        this.name = name;
        return this;
    }

    public RSShub url(String url) {
        this.url = url;
        return this;
    }

    public RSShub username(String username) {
        this.username = username;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof RSShub)) {
            return false;
        }
        RSShub rSShub = (RSShub) o;
        return Objects.equals(name, rSShub.name) && Objects.equals(url, rSShub.url)
                && Objects.equals(username, rSShub.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, url, username);
    }

    @Override
    public String toString() {
        return "{" + " name='" + getName() + "'" + ", url='" + getUrl() + "'" + ", username='" + getUsername() + "'"
                + "}";
    }

}
package com.zmh.springbootdemo.domain;

import java.util.Objects;

public class Collection {
    private String username;
    private String _id;

    public Collection() {
    }

    public Collection(String username, String _id) {
        this.username = username;
        this._id = _id;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String get_id() {
        return this._id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public Collection username(String username) {
        this.username = username;
        return this;
    }

    public Collection _id(String _id) {
        this._id = _id;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Collection)) {
            return false;
        }
        Collection collection = (Collection) o;
        return Objects.equals(username, collection.username) && Objects.equals(_id, collection._id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, _id);
    }

    @Override
    public String toString() {
        return "{" + " username='" + getUsername() + "'" + ", _id='" + get_id() + "'" + "}";
    }

}
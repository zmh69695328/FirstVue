package com.zmh.springbootdemo.domain;

import java.util.Objects;

public class User {
    private int id;
    private String username;
    private String password;
    private String email;
    private String age;
    private String profession;
    private String sex;
    private String avatar;

}

    public User() {
    }

    public User(int id, String username, String password, String email, String age, String profession, String sex,
            String avatar) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.age = age;
        this.profession = profession;
        this.sex = sex;
        this.avatar = avatar;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAge() {
        return this.age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getProfession() {
        return this.profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getSex() {
        return this.sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAvatar() {
        return this.avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public User id(int id) {
        this.id = id;
        return this;
    }

    public User username(String username) {
        this.username = username;
        return this;
    }

    public User password(String password) {
        this.password = password;
        return this;
    }

    public User email(String email) {
        this.email = email;
        return this;
    }

    public User age(String age) {
        this.age = age;
        return this;
    }

    public User profession(String profession) {
        this.profession = profession;
        return this;
    }

    public User sex(String sex) {
        this.sex = sex;
        return this;
    }

    public User avatar(String avatar) {
        this.avatar = avatar;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof User)) {
            return false;
        }
        User user = (User) o;
        return id == user.id && Objects.equals(username, user.username) && Objects.equals(password, user.password)
                && Objects.equals(email, user.email) && Objects.equals(age, user.age)
                && Objects.equals(profession, user.profession) && Objects.equals(sex, user.sex)
                && Objects.equals(avatar, user.avatar);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, password, email, age, profession, sex, avatar);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", username='" + getUsername() + "'" +
            ", password='" + getPassword() + "'" +
            ", email='" + getEmail() + "'" +
            ", age='" + getAge() + "'" +
            ", profession='" + getProfession() + "'" +
            ", sex='" + getSex() + "'" +
            ", avatar='" + getAvatar() + "'" +
            "}";
    }

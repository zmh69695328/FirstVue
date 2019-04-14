package com.zmh.springbootdemo.dao;

import java.util.Date;
import java.util.Objects;

import com.alibaba.fastjson.annotation.JSONField;

public class Person {
    private int id;
    private int age;
    private String name;

    public Person() {
    }

    public Person(int id, int age, String name) {
        this.id = id;
        this.age = age;
        this.name = name;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAge() {
        return this.age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Person id(int id) {
        this.id = id;
        return this;
    }

    public Person age(int age) {
        this.age = age;
        return this;
    }

    public Person name(String name) {
        this.name = name;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Person)) {
            return false;
        }
        Person person = (Person) o;
        return id == person.id && age == person.age && Objects.equals(name, person.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, age, name);
    }

    @Override
    public String toString() {
        return "{" + " id='" + getId() + "'" + ", age='" + getAge() + "'" + ", name='" + getName() + "'" + "}";
    }
}
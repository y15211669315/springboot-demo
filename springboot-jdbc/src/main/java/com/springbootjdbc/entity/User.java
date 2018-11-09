package com.springbootjdbc.entity;

import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

@Data
public class User implements Serializable {

    private Integer id;

    private String name;

    private Integer age;

    private String address;

    private String email;

    private Timestamp createStamp;

    public String getSql() {
        StringBuilder sb = new StringBuilder();
        sb.append("(").
                append(id).append(",").
                append("'").append(name).append("',").
                append(age).append(",").
                append("'").append(address).append("',").
                append("'").append(email).append("',").
                append("'").append(createStamp).append("')");
        return sb.toString();
    }

    public User() {

    }

    public User(int id, String name, int age, String adddress, String email) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.address = adddress;
        this.email = email;
        this.createStamp = new Timestamp(System.currentTimeMillis());
    }

}

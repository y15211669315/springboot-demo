package com.springbootjpa.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

/**   
* @Description: 用户实体
* @author ShengGuang.Ye
* @date 2018/11/2 14:14
* @version V1.0   
*/
@Entity(name = "t_user")
@Data
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private Integer age;

    private String address;

    private String email;

    @Column(name = "create_stamp")
    private Timestamp createStamp;

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

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                ", createStamp=" + createStamp +
                '}';
    }
}

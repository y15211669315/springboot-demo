package com.springbootimgoperation.entity;

import javax.persistence.*;
import java.util.Arrays;

@Entity
@Table(name = "t_img")
public class Img {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private byte[] img;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public byte[] getImg() {
        return img;
    }

    public void setImg(byte[] img) {
        this.img = img;
    }

    @Override
    public String toString() {
        return "Img{" +
                "id=" + id +'}';
    }

    public Img() {

    }

    public Img(byte[] imgs) {
        this.img = imgs;
    }
}

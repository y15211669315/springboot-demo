package com.shiro.springbootshiro.entity;

/**
 * 用户
 */
public class PriEntity {

    private Integer id;

    private String name;

    private String code;

    private String desc;

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public PriEntity(Integer id, String name, String code,String desc) {
        this.id = id;
        this.desc = desc;
        this.name = name;
        this.code = code;
    }
}

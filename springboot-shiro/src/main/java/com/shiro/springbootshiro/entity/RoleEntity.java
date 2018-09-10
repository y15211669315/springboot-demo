package com.shiro.springbootshiro.entity;

/**
 * 角色
 */
public class RoleEntity {

    private Integer id;

    private String name;

    private String code;

    private String desc;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public RoleEntity() {
    }

    public RoleEntity(Integer id, String name, String desc,String code) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.desc = desc;
    }
}

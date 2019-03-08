package com.springbootgcz;

/**
 * @author SG.Y
 * @version V1.0
 * @Description: 用户
 * @date 2019/3/8 17:51
 */
public class UserOne implements UserObServer {


    private String name;

    public UserOne(String name) {
        this.name = name;
    }

    @Override
    public void update(String name, String mess) {
        System.err.println(this.name + "看到了" + name + "发布了：" + mess);
    }
}

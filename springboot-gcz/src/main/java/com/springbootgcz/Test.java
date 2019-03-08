package com.springbootgcz;

public class Test {

    public static void main(String[] args) {
        // 实例化腾讯订阅公众号
        TenxunOne t = new TenxunOne();
        // 实例化用户
        UserOne u1 = new UserOne("小明");
        UserOne u2 = new UserOne("小绿");
        UserOne u3 = new UserOne("小红");
        // 用户关注订阅号
        t.add(u1);
        t.add(u2);
        t.add(u3);
        // 腾讯发布订阅信息
        t.tongzhi();
    }
}

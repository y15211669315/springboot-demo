package com.springbootwx;

import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringbootWxApplication {

    public static void main(String[] args) {
        int level = 100;    // 定义最大等级
        int exSize = 10;    // 定义初始化经验值
        int increment = 2;  // 定义经验值每级经验之隔

        int lev = 3;        // 等级
        int exp = 20;       // 经验

        // 判断exp经验是否满足提升到等级lev
        boolean bol = false;// true：满足：false：不满足
        for (int i = 1; i < level + 1; i++) {
            //经验值

        }


//        SpringApplication.run(SpringbootWxApplication.class, args);
    }

}

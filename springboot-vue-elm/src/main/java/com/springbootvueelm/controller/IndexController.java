package com.springbootvueelm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author ShengGuang.Ye
 * @version V1.0
 * @Description: 主页
 * @date 2018/6/28 16:29
 */
@Controller
public class IndexController {

    @GetMapping("/index")
    public String index() {
        return "index";
    }

}

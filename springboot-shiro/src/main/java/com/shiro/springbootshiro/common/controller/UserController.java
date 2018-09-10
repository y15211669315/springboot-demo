package com.shiro.springbootshiro.common.controller;

import com.shiro.springbootshiro.util.UserUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 用户接口
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @GetMapping
    @RequiresPermissions({"10001"})
    public Map find() {
        return UserUtil.userMap;
    }

}

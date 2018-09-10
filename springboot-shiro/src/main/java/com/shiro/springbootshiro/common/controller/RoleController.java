package com.shiro.springbootshiro.common.controller;

import com.shiro.springbootshiro.util.UserUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author ShengGuang.Ye
 * @version V1.0
 * @Description: 角色api
 * @date 2018/7/26 18:04
 */
@RestController
@RequestMapping("/role")
public class RoleController {

    @GetMapping
    @RequiresPermissions({"10002"})
    public Map find() {
        return UserUtil.roleMap;
    }
}

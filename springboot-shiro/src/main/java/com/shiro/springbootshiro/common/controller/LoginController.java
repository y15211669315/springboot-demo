package com.shiro.springbootshiro.common.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

/**
 * @author ShengGuang.Ye
 * @version V1.0
 * @Description: 登陆api
 * @date 2018/7/26 17:11
 */
@RestController
public class LoginController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 登陆页面
     *
     * @return
     */
    @GetMapping("/login")
    public HashMap<String, Object> login() {
        HashMap<String, Object> retMap = new HashMap<>();
        retMap.put("code", 200);
        retMap.put("message", "请先登录");
        return retMap;
    }

    /**
     * 登陆方法
     *
     * @param username 用户名
     * @param password 密码
     * @param remember 是否记住我
     * @return
     */
    @PostMapping("/login")
    public HashMap<String, Object> Login(@RequestParam("username") String username, @RequestParam("password") String password, @RequestParam(value = "remember", required = false, defaultValue = "false") Boolean remember) {
        HashMap<String, Object> retMap = new HashMap<>();
        Subject subject = SecurityUtils.getSubject();
        try {
            // 获得令牌
            UsernamePasswordToken token = new UsernamePasswordToken(username, password, remember);
            subject.login(token);
            retMap.put("message", "登陆成功");
        } catch (IncorrectCredentialsException ice) {
            logger.info("对用户【" + username + "】进行登录验证，验证未通过，错误的凭证！");
            retMap.put("error", "用户名或密码不正确！");
        } catch (UnknownAccountException uae) {
            logger.info("对用户【" + username + "】进行登录验证，验证未通过，未知账户！");
            retMap.put("error", "未知账户！");
        } catch (LockedAccountException lae) {
            logger.info("对用户【" + username + "】进行登录验证，验证未通过，账户锁定！");
            retMap.put("error", "账户已锁定！");
        } catch (ExcessiveAttemptsException eae) {
            logger.info("对用户【" + username + "】进行登录验证，验证未通过，错误次数太多！");
            retMap.put("error", "用户名或密码错误次数太多！");
        } catch (AuthenticationException ae) {
            logger.info("对用户【" + username + "】进行登录验证，验证未通过，堆栈轨迹如下：！");
            ae.printStackTrace();
            retMap.put("error", "用户名或密码不正确！");
        }
        retMap.put("code", 403);
        return retMap;
    }

    @GetMapping("/noPri")
    public String noPri(){
        return "权限不足";
    }

}

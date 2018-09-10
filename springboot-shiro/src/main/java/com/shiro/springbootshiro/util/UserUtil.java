package com.shiro.springbootshiro.util;

import com.shiro.springbootshiro.entity.PriEntity;
import com.shiro.springbootshiro.entity.RoleEntity;
import com.shiro.springbootshiro.entity.UserEntity;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 用户工具类
 */
public class UserUtil {

    // 虚拟用户集
    public static HashMap<String, UserEntity> userMap = new HashMap<>();

    // 虚拟角色集
    public static HashMap<String, RoleEntity> roleMap = new HashMap<>();

    // 虚拟权限集
    public static HashMap<String, PriEntity> priMap = new HashMap<>();

    // 定义虚拟用户
    public static void loadUser() {
        // 用户
        for (int i = 1; i <= 2; i++) {
            ByteSource byteS = ByteSource.Util.bytes("user" + i);
            String password = new SimpleHash("MD5", "pass" + i, byteS, 1024).toString();
            System.err.println("user" + i + ":" + password);
            userMap.put("user" + i, new UserEntity(i, "user" + i, password, i % 2 == 0));
        }
        // 角色
        roleMap.put("role_1", new RoleEntity(1, "admin", "管理员", "10001"));
        roleMap.put("role_2", new RoleEntity(2, "小三", "小三", "10002"));
        // 权限
        priMap.put("pri_1", new PriEntity(1, "pri", "10001", "用户查询"));
        priMap.put("pri_2", new PriEntity(2, "pri", "10002", "角色查询"));
    }

    /**
     * 根据用户名获取用户
     *
     * @param username
     */
    public static UserEntity getUser(String username) {
        // 加载虚拟用户
        if (userMap.size() < 1) loadUser();
        return userMap.get(username);
    }

    /**
     * 根据用户id获取角色
     *
     * @param userid
     */
    public static List<RoleEntity> getRole(Integer userid) {
        ArrayList<RoleEntity> list = new ArrayList<>();
        list.add(roleMap.get("role_" + userid));
        return list;
    }

    /**
     * 根据角色id获取权限
     *
     * @param roleid
     */
    public static List<PriEntity> getPri(Integer roleid) {
        // 加载虚拟用户
        ArrayList<PriEntity> list = new ArrayList<>();
        list.add(priMap.get("pri_" + roleid));
        return list;
    }


}

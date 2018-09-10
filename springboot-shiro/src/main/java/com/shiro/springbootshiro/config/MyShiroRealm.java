package com.shiro.springbootshiro.config;

import com.shiro.springbootshiro.entity.PriEntity;
import com.shiro.springbootshiro.entity.RoleEntity;
import com.shiro.springbootshiro.entity.UserEntity;
import com.shiro.springbootshiro.util.UserUtil;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MyShiroRealm extends AuthorizingRealm {

    /**
     * 提供用户信息，返回权限信息
     *
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        // 获取当前登陆用户
        UserEntity user = (UserEntity) principals.getPrimaryPrincipal();

        // 根据用户id查找角色
        List<RoleEntity> roles = UserUtil.getRole(user.getId());
        Set<String> roleSet = new HashSet<>();
        Set<String> privilegeSet = new HashSet<>();

        // 遍历角色查找角色拥有的权限code
        for (RoleEntity item : roles) {
            roleSet.add(item.getCode());
            for (PriEntity pri : UserUtil.getPri(item.getId())) {
                privilegeSet.add(pri.getCode());
            }
        }
        // 将角色编码组成的Set提供给授权info
        authorizationInfo.setRoles(roleSet);
        // 将权限编码组成的Set提供给info
        authorizationInfo.setStringPermissions(privilegeSet);
        return authorizationInfo;
    }

    /**
     * 提供帐户信息，返回认证信息
     *
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String userName = (String) authenticationToken.getPrincipal();
        UserEntity user = UserUtil.getUser(userName);
        if (user == null) {
            //用户不存在就抛出异常
            throw new UnknownAccountException();
        }
        if (user.getEnabled()) {
            //用户被锁定就抛异常
            throw new LockedAccountException();
        }
        //密码可以通过SimpleHash加密，然后保存 进数据库。
        //此处是获取数据库内的账号、密码、盐值，保存到登陆信息info中
        // TODO 盐值似乎有问题，密码匹配不上
//        SimpleAuthenticationInfo authenticationInfo =
//                new SimpleAuthenticationInfo(user, user.getPassword(), ByteSource.Util.bytes(user.getUsername()), getName());
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(user, user.getPassword(), getName());
        return authenticationInfo;
    }

//    /**
//     * 认证密码匹配调用方法
//     */
//    @Override
//    protected void assertCredentialsMatch(AuthenticationToken authcToken, AuthenticationInfo info) throws AuthenticationException {
//        UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
//        // 若单点登录，则使用单点登录授权方法。
//        if (token.toString().equals(token.getParams())){
//            // sso密钥+用户名+日期，进行md5加密，举例： Digests.md5(secretKey+username+20150101)）
//            String secretKey = Global.getConfig("shiro.sso.secretKey");
//            String password = Digests.md5(secretKey + token.getUsername() + DateUtils.getDate("yyyyMMdd"));
//            if (password.equals(String.valueOf(token.getPassword()))){
//                return;
//            }
//        }
//        super.assertCredentialsMatch(token, info);
//    }
}

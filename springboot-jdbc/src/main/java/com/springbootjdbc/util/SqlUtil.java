package com.springbootjdbc.util;

import lombok.Getter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 * @author ShengGuang.Ye
 * @version V1.0
 * @Description: SQL操作工具类
 * @date 2018/10/26 15:58
 */
@Getter
public class SqlUtil {

    private Connection connection;

    /**
     * 有参构造，实例化必须传入数据库连接对象
     *
     * @param connection
     */
    public SqlUtil(Connection connection) {
        this.connection = connection;
    }

    /**
     * 执行保存
     *
     * @param sql
     * @param params
     * @return
     */
    public boolean insert(String sql, List<Object> params) {
        try {
            // 获得注入对象
            PreparedStatement ps = connection.prepareStatement(sql);
            // 填充参数
            if (params != null) {
                for (int i = 0; i < params.size(); i++) {
                    ps.setObject(i + 1, params.get(i));
                }
            }
            // 执行sql
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


}

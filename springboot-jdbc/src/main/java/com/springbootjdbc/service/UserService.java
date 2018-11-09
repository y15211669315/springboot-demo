package com.springbootjdbc.service;

import com.springbootjdbc.config.JdbcConf;
import com.springbootjdbc.util.SqlUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Service("userService")
public class UserService {

    @Autowired
    private JdbcConf jdbcConf;

    /**
     * 保存用户信息
     *
     * @param response
     */
    public void save(HttpServletResponse response, Integer size) throws IOException {
        // 获得输出对象
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        if (size == null) {
            out.println("size参数为必传");
        } else {
            // Sql工具类
            SqlUtil su = new SqlUtil(jdbcConf.getConnection());
            long time = System.currentTimeMillis();
            out.println("开始执行SQL插入，插入数量：" + size);
            for (int i = 1; i <= size; i++) {
                // 存储参数
                List<Object> params = new ArrayList<>();
                String sql = "insert into t_user values(?,?,?,?,?,?)";
                params.add(i);
                params.add("小" + i);
                params.add(i);
                params.add("xxx村第" + i + "组");
                params.add(i + "xxxx@sina.cn");
                params.add(new Timestamp(System.currentTimeMillis()));
                // 插入
                su.insert(sql, params);
//                if (i < size) {
//                    sb.append(",");
//                }
            }
            out.println("SQL插入结束，用时:" + (System.currentTimeMillis() - time));
        }
    }

    public static void main(String[] args) {
        int size = 31;
        System.err.println(30/2);
    }

}

package com.springbootjdbc.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.scheduling.annotation.Async;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Vector;

@Configuration
//@ConfigurationProperties(prefix = "com.mysql")
public class JdbcConf implements ApplicationListener<ContextRefreshedEvent> {

    @Value("${com.mysql.driver}")
    private String driver;      // 驱动

    @Value("${com.mysql.url}")
    private String url;         // 连接地址

    @Value("${com.mysql.username}")
    private String username;    // 用户名

    @Value("${com.mysql.password}")
    private String password;    // 密码

    @Value("${com.mysql.minIdle}")
    private Integer minIdle;    // 最小空闲连接数

    @Value("${com.mysql.maxIdle}")
    private Integer maxIdle;    // 最大空闲连接数

    @Value("${com.mysql.maxActive}")
    private Integer maxActive;  // 最大连接数

    @Value("${com.mysql.initialSize}")
    private Integer initialSize;// 初始化连接大小

    private Vector<Connection> conns = new Vector<>();  // 数据库连接池

    private Integer currentCount = 0;// 当前连接池连接数

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 获得mysql连接
     *
     * @return 返回连接对象
     */
    private Connection connectionDB() {
        try {
            Class.forName(driver);
            return DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException e) {
            logger.error("找不到Mysql驱动类：" + driver);
            e.printStackTrace();
        } catch (SQLException e) {
            logger.error("连接Mysql异常.");
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获得mysql连接对象
     *
     * @return
     */
    public synchronized Connection getConnection() {
        Connection conn = null;
        if (conns.size() > 0) { // 从空闲连接中获取连接对象
            conn = conns.get(conns.size() - 1);
        } else if (currentCount < maxActive) {   // 如果空闲连接池用完了，并且当前连接数小于最大连接数，则获取新的连接对象
            conn = connectionDB();
            currentCount++;
        }
        supplementConnsSize();  // 连接补充
        return conn;
    }

    /**
     * 释放连接对象，放入到连接池集合中
     *
     * @param conn
     */
    public void releaseConnection(Connection conn) {
        if (conns.size() >= maxIdle) {  // 如果当前空闲大于等于最大空闲连接则关闭连接
            closeConnection(conn);
            currentCount--;
        } else {
            conns.add(conn);
        }
    }

    /**
     * 填充连接池集合大小
     *
     * @return
     */
    @Async("myExecutor")
    public synchronized void supplementConnsSize() {
        do {
            int size = conns.size();
            if (currentCount < maxActive) {     // 当前连接数小于最大连接数
                if (size < minIdle) {   // 空闲连接小于最小空闲数则添加空闲连接
                    conns.add(connectionDB());
                    currentCount++;
                    continue;
                } else if (size > maxIdle) {    // 如果空闲连接大于最大空闲连接数则关闭多余的连接
                    closeConnection(conns.get(size - 1));
                    conns.remove(size - 1);
                    currentCount--;
                }
            }
        } while (currentCount < maxActive && (conns.size() < minIdle || conns.size() > maxIdle));
    }

    /**
     * 关闭数据库连接
     *
     * @param conn
     */
    private void closeConnection(Connection conn) {
        try {
            conn.close();
        } catch (SQLException e) {
            logger.error("关闭数据库连接对象异常.");
            e.printStackTrace();
        }
    }

    /**
     * 初始化连接池连接数量
     */
    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        for (int i = 0; i < initialSize; i++) {
            Connection conn = connectionDB();
            if (conn != null) {
                conns.add(conn);
                currentCount++;
            } else {
                logger.error("初始化连接数据库失败.");
                break;
            }
        }
    }
}

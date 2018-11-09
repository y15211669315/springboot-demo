package com.springbootjpa.repository;

import com.springbootjpa.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Stream;

/**
 * @author ShengGuang.Ye
 * @version V1.0
 * @Description: 用户数据库交互操作
 * @date 2018/09/30 10:14
 */
public interface UserRepository extends JpaRepository<User, Integer>, JpaSpecificationExecutor<User> {

    // 普通查询
    @Query(value = "select t.* from t_user t", nativeQuery = true)
    List<User> findList();

    // 流式查询
    @Transactional(readOnly = true)
    @Query(value = "select t from t_user t")
    Stream<User> findStream();

}

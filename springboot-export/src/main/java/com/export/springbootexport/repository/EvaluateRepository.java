package com.export.springbootexport.repository;

import com.export.springbootexport.model.Evaluate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

/**
 * @author ShengGuang.Ye
 * @version V1.0
 * @Description: 小课堂
 * @date 2018/8/23 13:56
 */
public interface EvaluateRepository extends JpaRepository<Evaluate, Long>, JpaSpecificationExecutor<Evaluate> {

    /**
     * 查询
     *
     * @param pageable
     * @return
     */
    @Query(value = "select t.* from t_evaluate t where t.`status`=0", nativeQuery = true, countQuery = "select COUNT(1) from t_evaluate t where t.`status`=0")
    Page<Evaluate> findAll(Pageable pageable);

    /**
     * 查询
     *
     * @param time1 开始时间
     * @param time2 结束时间
     * @return
     */
    @Query(value = "select t.* from t_evaluate t where t.`status`=0 and (t.create_time>=?1 and t.create_time <= ?2)", nativeQuery = true)
    List<Evaluate> findByTime(Date time1, Date time2);

}

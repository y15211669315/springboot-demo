package com.springbootimgoperation.rep;

import com.springbootimgoperation.entity.Img;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author ShengGuang.Ye
 * @version V1.0
 * @Description: 图片
 * @date 2018/11/08 18:35
 */

public interface ImgRep extends JpaRepository<Img, Integer>, JpaSpecificationExecutor<Img> {

    @Query(value = "select t.* from t_img t", nativeQuery = true)
    List<Img> selectAll();

}


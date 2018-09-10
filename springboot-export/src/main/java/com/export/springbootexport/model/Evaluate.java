package com.export.springbootexport.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.util.StringUtils;

import javax.persistence.*;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author ShengGuang.Ye
 * @version V1.0
 * @Description: 评价表
 * @date 2018/8/22 11:26
 */
@Data
@Entity(name = "t_evaluate")
public class Evaluate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "user_id")
    private Integer userId;

    private Integer cid;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Column(name = "create_time")
    private Timestamp createTime;

    private String content;

    @Transient
    private String[] title;

    @Transient
    private Integer[] orders;

    @Transient
    private Integer[] titleIndex;

    @Transient
    private Date time1;

    @Transient
    private Date time2;

    public void setTime1(String time1) throws ParseException {
        if (!StringUtils.isEmpty(time1)) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            this.time1 = sdf.parse(time1);
        }
    }

    public void setTime2(String time2) throws ParseException {
        if (!StringUtils.isEmpty(time2)) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            this.time2 = sdf.parse(time2);
        }
    }

    public Evaluate() {

    }

    public Evaluate(int id, int userId, String content, Timestamp time) {
        this.id = id;
        this.content = content;
        this.createTime = time;
        this.userId = userId;
    }

}

package com.export.springbootexport.dto;

import lombok.Data;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author ShengGuang.Ye
 * @version V1.0
 * @Description: 接收参数
 * @date 2018/9/6 14:21
 */
@Data
public class ExcelDto extends _RequestDto implements Serializable {

    private Date time1;

    private Date time2;

    public void setTime1(String time1) throws ParseException {
        if (time1 != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            this.time1 = sdf.parse(time1);
        }
    }

    public void setTime2(String time2) throws ParseException {
        if (time2 != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            this.time2 = sdf.parse(time2);
        }
    }

}

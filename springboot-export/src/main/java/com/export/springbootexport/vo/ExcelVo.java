package com.export.springbootexport.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author ShengGuang.Ye
 * @version V1.0
 * @Description: 表格信息
 * @date 2018/9/3 14:26
 */
@Data
public class ExcelVo implements Serializable {

    /**
     * 工作表名
     */
    private String sheetName;

    /**
     * 工作表名的标题列
     */
    private String[] titles;

    /**
     * 数据库字段
     */
    private String[] columns;

    private void columns() {
        this.columns = new String[]{"id", "content", "createTime", "userId"};
    }

    public ExcelVo() {
        this.columns();
    }

    public ExcelVo(String sheetName, String[] titles) {
        this.sheetName = sheetName;
        this.titles = titles;
        this.columns();
    }

}

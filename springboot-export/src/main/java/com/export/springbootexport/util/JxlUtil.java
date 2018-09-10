package com.export.springbootexport.util;

import com.export.springbootexport.model.Evaluate;
import com.export.springbootexport.vo.ExcelVo;
import jxl.Sheet;
import jxl.Workbook;
import jxl.write.*;
import jxl.write.Number;

import java.io.File;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ShengGuang.Ye
 * @version V1.0
 * @Description: 导入导出工具类
 * @date 2018/8/31 15:19
 */
public class JxlUtil {


    /**
     * @param title     标题集合
     * @param order     内容顺序-
     * @param contents  内容数据
     * @param sheetName 工作簿的名字
     * @param path      保存地址
     * @param page      页数
     */
    public static void export(String[] title, Integer[] order, List<Evaluate> contents, String sheetName, String path, int page, Integer[] titleIndex) {
        WritableWorkbook book = null;
        try {
            // 文件保存地址
            book = Workbook.createWorkbook(new File(path));
            // 生成第一页
            WritableSheet sheet = book.createSheet(sheetName, page);
            generatekTitle(sheet, order, title);    // 生成标题
            generatekContent(sheet, contents, order, titleIndex);   // 添加数据行
            // 写入数据并关闭文件
            book.write();
        } catch (Exception e) {
            LogUtil.pringException(e);
        } finally {
            if (book != null) {
                try {
                    book.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    // 添加标题
    private static void generatekTitle(WritableSheet sheet, Integer[] order, String[] title) throws WriteException {
        for (int i = 0; i < title.length; i++) {
            sheet.addCell(new Label(order[i], 0, title[i]));
        }
    }

    // 添加数据
    private static void generatekContent(WritableSheet sheet, List<Evaluate> contents, Integer[] order, Integer[] titleIndex) throws WriteException {
        for (int i = 1; i <= contents.size(); i++) {
            Evaluate e = contents.get(i - 1);       // 获得评价对象
            for (int j = 0; j < titleIndex.length; j++) {
                switch (titleIndex[j]) {
                    case 0:
                        sheet.addCell(new Number(order[j], i, e.getId()));
                        break;
                    case 1:
                        sheet.addCell(new Label(order[j], i, e.getContent()));
                        break;
                    case 2:
                        sheet.addCell(new Label(order[j], i, StringUtil.getDateStr(e.getCreateTime())));
                        break;
                    case 3:
                        sheet.addCell(new Number(order[j], i, e.getUserId()));
                        break;
                }
            }
        }
    }

    // 读取工作表名、标题列信息
    public static ExcelVo[] readSheet(Workbook book) {
        ExcelVo[] excelVos = new ExcelVo[book.getNumberOfSheets()];
        for (int i = 0; i < excelVos.length; i++) {
            Sheet sheet = book.getSheet(i);
            // 获得每个工作表的标题
            String[] titles = new String[sheet.getRow(0).clone().length];
            for (int j = 0; j < titles.length; j++) {
                titles[j] = sheet.getCell(j, 0).getContents();
            }
            excelVos[i] = new ExcelVo(sheet.getName(), titles);
        }
        return excelVos;
    }

    /**
     * 本地测试
     *
     * @param args
     */
    public static void main(String[] args) {
        String[] title = {"编号", "评论内容", "创建时间", "评论用户ID"};
        Integer[] titleIndex = {0, 1, 2, 3};
        Integer[] order = {2, 3, 1, 0};
        List<Evaluate> contents = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            contents.add(new Evaluate(i, i + 20, "么么皮" + i, new Timestamp(System.currentTimeMillis())));
        }
        export(title, order, contents, "没啥数据", "C:\\Ysg\\upload\\test.xls", 0, titleIndex);    // 执行导出
        System.err.println("OK");
    }

}

package com.export.springbootexport.service;

import com.export.springbootexport.dto.QueryDto;
import com.export.springbootexport.model.Evaluate;
import com.export.springbootexport.repository.EvaluateRepository;
import com.export.springbootexport.util.JxlUtil;
import com.export.springbootexport.vo.ExcelVo;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * @author ShengGuang.Ye
 * @version V1.0
 * @Description: 评价业务层
 * @date 2018/8/23 13:55
 */
@Service(value = "EvaluateService")
public class EvaluateService {

    @Autowired
    private EvaluateRepository evaluateRepository;

    /**
     * 查询
     *
     * @param pageable
     * @param evaluate
     * @return
     */
    public List<Evaluate> find(Evaluate evaluate, Pageable pageable) {
        return evaluateRepository.findAll(pageable).getContent();
    }

    /**
     * 查询
     *
     * @param time1
     * @param time2
     * @return
     */
    public List<Evaluate> findByTime(Date time1, Date time2) {
        return evaluateRepository.findByTime(time1, time2);
    }

    /**
     * 导出
     *
     * @param evaluate
     * @return
     */
    public String export(Evaluate evaluate) {
        List<Evaluate> list = evaluateRepository.findByTime(evaluate.getTime1(), evaluate.getTime2());
        String sheetName = new StringBuilder().append(evaluate.getTime1().getYear()).append("年").
                append(evaluate.getTime1().getMonth()).append("月").append(evaluate.getTime1().getDay()).append("日到").
                append(evaluate.getTime2().getYear()).append("年").
                append(evaluate.getTime2().getMonth()).append("月").append(evaluate.getTime2().getDay()).append("日的数据").toString();
        String path = "C:\\Ysg\\upload\\evaluate.xls";
        JxlUtil.export(evaluate.getTitle(), evaluate.getOrders(), list, sheetName, path, 0, evaluate.getTitleIndex());
        return "后台导出中……";
    }

    /**
     * 获取导入文件的信息（必须是excel文件）
     *
     * @param multipartFile
     * @return
     */
    public QueryDto getFileInfo(MultipartFile multipartFile) throws IOException, BiffException {
        HashMap<String, ExcelVo[]> map = new HashMap<>();
        // 获得数据流
        InputStream inputStream = multipartFile.getInputStream();
        // 调用获得工作表名，标题列的方法，将数据保存返回到前台
        map.put("excels", JxlUtil.readSheet(Workbook.getWorkbook(inputStream)));
        inputStream.close();    // 关闭流
        // 数据库字段
        return new QueryDto(map);
    }

    /**
     * 导入数据
     *
     * @param inputStream
     * @return
     */
    @Transactional
    public QueryDto import_(InputStream inputStream, int sheets, int[] column) throws IOException, BiffException {
        // 获取excel
        Workbook book = Workbook.getWorkbook(inputStream);
        List<Evaluate> evaluates = new ArrayList<>();   // 存储数据
        // 获取工作表
        Sheet sheet = book.getSheet(sheets);
        //获取内容数据
        for (int j = 1; j < sheet.getRows(); j++) {
            Cell[] cell = sheet.getRow(j);
            Evaluate evaluate = new Evaluate();
            for (int i = 0; i < column.length; i++) {
                switch (column[i]) {
                    case 0:
                        evaluate.setId(Integer.valueOf(cell[i].getContents()));
                        break;
                    case 1:
                        evaluate.setContent(cell[i].getContents());
                        break;
                    case 2:
                        evaluate.setCreateTime(Timestamp.valueOf(cell[i].getContents()));
                        break;
                    case 3:
                        evaluate.setUserId(Integer.valueOf(cell[i].getContents()));
                        break;
                }
            }
            evaluates.add(evaluate);
        }
        // 保存数据
        evaluateRepository.saveAll(evaluates);
        return new QueryDto("导入成功数：" + sheet.getRows());
    }

    /**
     * 导入数据
     *
     * @param inputStream
     * @return
     *//*
    @Transactional
    public QueryDto import_(InputStream inputStream, int[] sheets, int[][] column) throws IOException, BiffException {
        // 获取excel
        Workbook book = Workbook.getWorkbook(inputStream);
        List<Evaluate> evaluates = new ArrayList<>();   // 存储数据
        for (int i = 0; i < sheets.length; i++) {
            // 获取工作表
            Sheet sheet = book.getSheet(sheets[i]);
            //获取内容数据
            for (int j = 1; j < sheet.getRows(); j++) {
                Cell[] cell = sheet.getRow(j);
                Evaluate evaluate = new Evaluate();
                switch (column[i][0]) {
                    case 0:
                        evaluate.setId(Integer.valueOf(cell[0].getContents()));
                        break;
                    case 1:
                        evaluate.setContent(cell[1].getContents());
                        break;
                    case 2:
                        evaluate.setCreateTime(Timestamp.valueOf(cell[2].getContents()));
                        break;
                    case 3:
                        evaluate.setUserId(Integer.valueOf(cell[3].getContents()));
                        break;
                }
                evaluates.add(evaluate);
            }
        }
        // 保存数据
        try {
            evaluateRepository.saveAll(evaluates);
        } catch (Exception e) {
            e.printStackTrace();

        }
        return new QueryDto("导入数据内容成功");
    }*/

}

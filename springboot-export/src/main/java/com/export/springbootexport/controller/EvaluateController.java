package com.export.springbootexport.controller;


import com.export.springbootexport.dto.ExcelDto;
import com.export.springbootexport.dto.PageDto;
import com.export.springbootexport.dto.QueryDto;
import com.export.springbootexport.model.Evaluate;
import com.export.springbootexport.service.EvaluateService;
import com.export.springbootexport.vo.ExcelVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jxl.read.biff.BiffException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ShengGuang.Ye
 * @version V1.0
 * @Description: 评价接口API
 * @date 2018/8/23 13:53
 */
@Api(description = "评价接口APi")
@Controller
@RequestMapping("/evaluate")
public class EvaluateController {

    @Autowired
    private EvaluateService evaluateService;

    @ApiOperation("评价查询")
    @GetMapping
    public String find(PageDto pageDto, Evaluate evaluate, Model model) {
        model.addAttribute("list", evaluateService.find(evaluate, PageRequest.of(pageDto.getPage(), pageDto.getRows(), pageDto.getSort())));
        return "index.html";
    }

    @ApiOperation("评价查询")
    @GetMapping("/time")
    @ResponseBody
    public Map<String, Object> find(ExcelDto dto) {
        Map<String, Object> map = new HashMap<>();
        map.put("list", evaluateService.findByTime(dto.getTime1(), dto.getTime2()));
        return map;
    }

    @ApiOperation("获取后台字段")
    @GetMapping("/column")
    @ResponseBody
    public String[] getColumn() {
        return new ExcelVo().getColumns();
    }

    @ApiOperation("导出功能")
    @ResponseBody
    @PostMapping("/export")
    public String export(@RequestBody Evaluate evaluate) {
        return evaluateService.export(evaluate);
    }

    @ApiOperation("导入功能")
    @ResponseBody
    @PostMapping("import")
    public QueryDto import_(@RequestParam("file") MultipartFile multipartFile, int sheets, int[] columns) throws IOException, BiffException {
        return evaluateService.import_(multipartFile.getInputStream(), sheets, columns);
    }

    @ApiOperation("获取文件信息")
    @ResponseBody
    @PostMapping("getFileInfo")
    public QueryDto getFileInfo(@RequestParam("file") MultipartFile multipartFile) throws IOException, BiffException {
        return evaluateService.getFileInfo(multipartFile);
    }

    /*@ApiOperation("导入功能")
    @ResponseBody
    @PostMapping("import")
    public QueryDto import_(@RequestParam("file") MultipartFile multipartFile, int[] sheets, int[][] column) throws IOException, BiffException {
        return evaluateService.import_(multipartFile.getInputStream(), sheets, column);
    }*/

}

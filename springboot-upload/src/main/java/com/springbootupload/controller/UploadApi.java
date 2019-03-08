package com.springbootupload.controller;

import com.springbootupload.service.UploadService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author ShengGuang.Ye
 * @version V1.0
 * @Description: 文件上传Api
 * @date 2018/12/11 16:01
 */
@Api(description = "文件上传API")
@RestController
@RequestMapping("/upload")
public class UploadApi {

    @Autowired
    private UploadService uploadService;


    /**
     * 上传图片
     *
     * @param file
     */
    @ApiOperation("上传图片")
    @PostMapping("/images")
    public String uploadImage(@RequestParam(value = "file", required = false) MultipartFile file, Integer id) {
        return uploadService.uploadImages(file, id);
    }

}

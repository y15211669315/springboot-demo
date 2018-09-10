package com.springbootfastdfs.controller;

import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
public class TestApi {


    @Autowired
    private FastFileStorageClient fastFileStorageClient;

    @PostMapping("/uppload")
    public StorePath test(@RequestParam MultipartFile file) throws IOException {
        StorePath storePath = fastFileStorageClient.uploadFile(file.getInputStream(), file.getSize(), FilenameUtils.getExtension(file.getOriginalFilename()), null);
        return storePath;
    }

    @GetMapping("/download")
    public String downLoad(@RequestParam String path) {
//        fastFileStorageClient.downloadFile(path);
        return "";
    }

}

package com.springbootupload.service;

import com.springbootupload.utils.UploadUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author ShengGuang.Ye
 * @version V1.0
 * @Description: 上传业务处理
 * @date 2018/12/11 16:23
 */
@Service
public class UploadService {

    @Value("${upload.save.path}")
    private String directory;

    // 密钥
    private String key;

    /**
     * 上传图片
     *
     * @param file
     * @param id
     */
    public boolean uploadImage(MultipartFile file, Integer id) {
        boolean bool = false;
        try {
            // 获取文件名
            String filenameSuffix = file.getOriginalFilename();
            filenameSuffix = filenameSuffix.substring(filenameSuffix.lastIndexOf('.'), filenameSuffix.length());
            String filename = DigestUtils.md5DigestAsHex((id + key).getBytes()) + filenameSuffix;
            // 生成目录
            StringBuffer directory = new StringBuffer();
            directory.append(this.directory).append("/")
                    .append(filename, 0, 2).append("/")
                    .append(filename, 2, 4).append("/")
                    .append(filename, 4, 6).append("/");
            bool = UploadUtils.saveFile(file.getInputStream(), directory.toString(), filename);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bool;
    }

    /**
     * 上传多个图片
     *
     * @param id
     */
    public String uploadImages(MultipartFile file, Integer id) {
        String path = "";
        // 获取文件名
        String filenameSuffix = file.getOriginalFilename();
        filenameSuffix = filenameSuffix.substring(filenameSuffix.lastIndexOf('.'), filenameSuffix.length());
        String filename = DigestUtils.md5DigestAsHex((id + key).getBytes()) + filenameSuffix;
        // 生成目录
        StringBuffer directory = new StringBuffer();
        directory.append("/")
                .append(filename, 0, 2).append("/")
                .append(filename, 2, 4).append("/")
                .append(filename, 4, 6).append("/");
        try {
            UploadUtils.saveFile(file.getInputStream(), this.directory + directory.toString(), filename);
            path = directory.toString() + filename;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return path;
    }

}

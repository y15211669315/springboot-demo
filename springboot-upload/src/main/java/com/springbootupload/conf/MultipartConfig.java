package com.springbootupload.conf;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.MultipartConfigElement;

/**
 * @author ShengGuang.Ye
 * @version V1.0
 * @Description: 文件上传下载配置
 * @date 2018/07/18 09:45
 */
@Configuration
public class MultipartConfig {

    @Value("${upload.max.size}")
    private String maxFileSize;

    @Value("${upload.max.request.size}")
    private String maxRequestSize;

    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        factory.setMaxFileSize(maxFileSize);
        factory.setMaxRequestSize(maxRequestSize);
        return factory.createMultipartConfig();
    }
}

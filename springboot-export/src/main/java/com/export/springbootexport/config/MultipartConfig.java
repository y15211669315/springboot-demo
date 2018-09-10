package com.export.springbootexport.config;

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

    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        factory.setMaxFileSize("25MB");
        factory.setMaxRequestSize("30MB");
        return factory.createMultipartConfig();
    }
}

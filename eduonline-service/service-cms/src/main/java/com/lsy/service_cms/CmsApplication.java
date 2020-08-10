package com.lsy.service_cms;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Author : Lo Shu-ngan
 * @Classname CmsApplication
 * @Description CMS启动类
 * @Date 2020/08/10 19:42
 */
@SpringBootApplication
@MapperScan(basePackages = "com.lsy.service_cms.mapper")
@ComponentScan("com.lsy")
public class CmsApplication {
    public static void main(String[] args) {
        SpringApplication.run(CmsApplication.class,args);
    }
}

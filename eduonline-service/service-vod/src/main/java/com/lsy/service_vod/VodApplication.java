package com.lsy.service_vod;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Author : Lo Shu-ngan
 * @Classname VodApplication
 * @Description 视频点播启动类
 * @Date 2020/08/08 21:16
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@ComponentScan("com.lsy")
public class VodApplication {
    public static void main(String[] args) {
        SpringApplication.run(VodApplication.class,args);
    }
}

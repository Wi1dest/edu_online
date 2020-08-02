package com.lsy.service_edu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Author : Lo Shu-ngan
 * @Classname Application
 * @Description TODO
 * @Date 2020/08/02 19:19
 */
@SpringBootApplication
@ComponentScan(basePackages = "com.lsy")
public class EduApplication {
    public static void main(String[] args) {
        SpringApplication.run(EduApplication.class,args);
    }
}

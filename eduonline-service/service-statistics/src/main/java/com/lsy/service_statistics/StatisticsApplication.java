package com.lsy.service_statistics;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @Author : Lo Shu-ngan
 * @Classname StatisticsApplication
 * @Description 统计启动器
 * @Date 2020/08/16 01:58
 */
@SpringBootApplication
@ComponentScan("com.lsy")
@MapperScan("com.lsy.service_statistics.mapper")
@EnableFeignClients
@EnableDiscoveryClient
@EnableScheduling
public class StatisticsApplication {
    public static void main(String[] args) {
        SpringApplication.run(StatisticsApplication.class,args);
    }
}

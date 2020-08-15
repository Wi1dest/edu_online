package com.lsy.service_order;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Author : Lo Shu-ngan
 * @Classname OrderApplication
 * @Description 订单模块启动类
 * @Date 2020/08/15 00:34
 */
@SpringBootApplication
@ComponentScan("com.lsy")
@MapperScan("com.lsy.service_order.mapper")
@EnableDiscoveryClient
@EnableFeignClients
public class OrderApplication {
    public static void main(String[] args) {
        SpringApplication.run(OrderApplication.class,args);
    }
}

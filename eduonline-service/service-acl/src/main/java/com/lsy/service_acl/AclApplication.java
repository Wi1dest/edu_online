package com.lsy.service_acl;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Author : Lo Shu-ngan
 * @Classname AclApplication
 * @Description ACL启动类
 * @Date 2020/08/18 00:12
 */
@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan("com.lsy")
@MapperScan("com.lsy.service_acl.mapper")
public class AclApplication {
    public static void main(String[] args) {
        SpringApplication.run(AclApplication.class,args);
    }
}

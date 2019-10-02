package com.zzx.humor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;

/**
 * TODO 认证中心启动类
 */
@RefreshScope
@SpringBootApplication
@EnableDiscoveryClient
public class HumorAuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(HumorAuthApplication.class, args);
    }

}

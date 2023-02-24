package cn.datasset.yipandian.client;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author zhoukun
 * @version 1.0.0
 */
@EnableCaching
// @EnableDubbo
@ComponentScan(basePackages = {"cn.datasset.yipandian.client"})
@MapperScan({ "cn.datasset.yipandian.client.mapper"})
@SpringBootApplication
@EnableScheduling
public class ClientApplication {
    public static void main(String[] args) {
        SpringApplication.run(ClientApplication.class, args);
    }
}

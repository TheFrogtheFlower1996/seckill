package com.zh.seckill;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootVersion;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author zh
 * @date 2022/4/11 14:01
 * @description:说明
 */
@SpringBootApplication
@MapperScan("com.zh.seckill.pojo")
public class SeckillApplication {
    public static void main(String[] args) {
        System.out.println(SpringBootVersion.getVersion());
        SpringApplication.run(SeckillApplication.class,args);
    }
}

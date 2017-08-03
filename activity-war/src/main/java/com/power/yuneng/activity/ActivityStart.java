package com.power.yuneng.activity;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2017/7/27.
 */
@SpringBootApplication
@MapperScan("com.power.yuneng.activity.dao.**")
@ComponentScan("com.power")
@ImportResource(locations={"classpath*:dubbo.xml"})
@EnableScheduling
public class ActivityStart {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(ActivityStart.class, args);
    }
}

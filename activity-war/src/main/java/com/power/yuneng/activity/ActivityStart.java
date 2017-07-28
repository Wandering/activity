package com.power.yuneng.activity;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

/**
 * Created by Administrator on 2017/7/27.
 */
@SpringBootApplication
@MapperScan("com.power.yuneng.activity.dao")
@ImportResource(locations={"classpath*:dubbo.xml"})
public class ActivityStart {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(ActivityStart.class, args);
    }
}

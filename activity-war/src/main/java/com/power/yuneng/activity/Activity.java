package com.power.yuneng.activity;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

/**
 * Created by Administrator on 2017/7/27.
 */
@SpringBootApplication
@MapperScan("com.power.dao")
@ImportResource(locations={"classpath*:config/zgk-dubbo-consumer.xml"})
public class Activity {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Activity.class, args);
    }

}

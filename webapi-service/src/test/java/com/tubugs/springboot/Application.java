package com.tubugs.springboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by xuzhang on 2017/8/26.
 */
@SpringBootApplication
@MapperScan(basePackages = "com.tubugs.springboot.dao.mapper")
public class Application {
    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(Application.class);
        Map<String, Object> defaultMap = new HashMap<String, Object>();
        application.setDefaultProperties(defaultMap);
        application.run(args);
    }
}

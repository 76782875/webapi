package com.tubugs.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by xuzhang on 2017/8/26.
 */
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(Application.class);
        Map<String, Object> defaultMap = new HashMap<String, Object>();
        application.setDefaultProperties(defaultMap);
        application.run(args);
    }
}

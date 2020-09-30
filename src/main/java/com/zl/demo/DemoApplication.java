package com.zl.demo;

import com.zl.demo.config.MyCommandRunner;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
@MapperScan("com.zl.demo.mapper")
public class DemoApplication {

    private static final Logger logger = LoggerFactory.getLogger(DemoApplication.class);

    public static void main(String[] args) {

        SpringApplication.run(DemoApplication.class, args);
        MyCommandRunner.LoginBrowser();
        logger.info("========================启动完毕========================");
    }

}

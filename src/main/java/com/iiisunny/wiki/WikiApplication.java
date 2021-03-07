package com.iiisunny.wiki;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

@SpringBootApplication(scanBasePackages = {"com.iiisunny.wiki"})
@MapperScan("")
public class WikiApplication {

    private static final Logger LOG = LoggerFactory.getLogger(WikiApplication.class);

    public static void main(String[] args) {

        SpringApplication springApplication = new SpringApplication(WikiApplication.class);

        Environment env = springApplication.run(args).getEnvironment();
        LOG.info("项目启动成功");
        LOG.info("地址：\thttp://127.0.0.1:{}", env.getProperty("server.port"));
    }

}

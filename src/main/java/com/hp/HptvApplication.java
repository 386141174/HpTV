package com.hp;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@ServletComponentScan
@MapperScan("com.hp.dao")
@SpringBootApplication
@EnableTransactionManagement
@ComponentScan(basePackages = {"com.hp.*"})
public class HptvApplication {

    public static void main(String[] args) {
        SpringApplication.run(HptvApplication.class, args);
    }

}

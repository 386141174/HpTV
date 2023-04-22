package com.hp;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.system.ApplicationHome;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@ServletComponentScan
@MapperScan("com.hp.dao")
@SpringBootApplication
@EnableTransactionManagement
@ComponentScan(basePackages = {"com.hp.*"})

public class HptvApplication implements WebMvcConfigurer {

    public static void main(String[] args) {
        SpringApplication.run(HptvApplication.class, args);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry){
        /*
            addResoureHandler：指的是对外暴露的访问路径
            addResourceLocations：指的是内部文件放置的目录
        */
        ApplicationHome applicationHome = new ApplicationHome(this.getClass());
        String path = applicationHome.getDir().getParentFile().getParentFile().getAbsolutePath() + "/src/main/resources/static/image/";
        registry.addResourceHandler("/imctemp-rainy/**").addResourceLocations("file:" + path);
    }
}

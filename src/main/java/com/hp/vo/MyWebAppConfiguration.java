package com.hp.vo;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.config.annotation.*;

import java.util.List;

@Configuration
public class MyWebAppConfiguration implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/img/**").addResourceLocations("file:D:/file/");
        registry.addResourceHandler("/pic/**").addResourceLocations("file:D:/pic/");
        registry.addResourceHandler("/file/**").addResourceLocations("file:D:/file/");
    }

}

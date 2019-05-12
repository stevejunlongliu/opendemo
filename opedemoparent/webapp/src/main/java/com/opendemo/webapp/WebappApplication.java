package com.opendemo.webapp;

import com.opendemo.core.common.aop.SessionInfoArgumentResolver;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;

@SpringBootApplication
@ComponentScan(value = "com.opendemo")
public class WebappApplication extends WebMvcConfigurerAdapter {

    public static void main(String[] args) {

        SpringApplication.run(WebappApplication.class, args);
    }


    //todo 如何更优雅的注入
    //自动注入登陆的用户状态
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        super.addArgumentResolvers(argumentResolvers);
        argumentResolvers.add(new SessionInfoArgumentResolver());
    }

    //todo session通用鉴权
    //todo
}

package com.bushemi;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@Configuration
public class DispatcherConfig implements WebMvcConfigurer {


    @Bean
    public ViewResolver viewResolver() {
        String suffix = ".jsp";
        String prefix = "/WEB-INF/views/";

        InternalResourceViewResolver bean = new InternalResourceViewResolver(prefix, suffix);
        bean.setViewClass(JstlView.class);


        return bean;
    }

}
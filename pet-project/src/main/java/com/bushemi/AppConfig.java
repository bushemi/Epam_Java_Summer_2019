package com.bushemi;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(value = {"com.bushemi.converters",
        "com.bushemi.dao",
        "com.bushemi.service",
        "com.bushemi.web"})
public class AppConfig {

}

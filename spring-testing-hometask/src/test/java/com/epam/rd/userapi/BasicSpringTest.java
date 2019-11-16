package com.epam.rd.userapi;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.AnnotationConfigWebContextLoader;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@WebAppConfiguration
@ContextConfiguration(classes = {BasicSpringTest.TestContextConfiguration.class},
        loader = AnnotationConfigWebContextLoader.class)
@TestPropertySource(properties = {"spring.config.location = classpath:application.properties"})
@RunWith(SpringRunner.class)
public abstract class BasicSpringTest {

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected ObjectMapper objectMapper;

    @Autowired
    protected RestTemplate restTemplate;

    @Configuration
    @EnableWebMvc
    @ComponentScan("com.epam.rd.userapi")
    protected static class TestContextConfiguration {
        @Bean
        public MockMvc mockMvc(WebApplicationContext webApplicationContext) {
            System.out.println("webApplicationContext = " + webApplicationContext);
            return MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        }

        @Bean
        public RestTemplate restTemplate() {
//            return Mockito.mock(RestTemplate.class);
            return new RestTemplate();
        }

        @Bean
        public ObjectMapper objectMapper() {
            return new ObjectMapper();
        }
    }
}

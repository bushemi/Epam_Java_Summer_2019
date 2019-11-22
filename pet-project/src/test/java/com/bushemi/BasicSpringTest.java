package com.bushemi;

import com.bushemi.service.implementations.DbConnectionPoolServiceImpl;
import com.bushemi.service.interfaces.DbConnectionService;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.AnnotationConfigWebContextLoader;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import static org.mockito.Mockito.mock;

@WebAppConfiguration
@ContextConfiguration(classes = {BasicSpringTest.TestContextConfiguration.class},
        loader = AnnotationConfigWebContextLoader.class)
@RunWith(SpringRunner.class)
public abstract class BasicSpringTest {

    @Autowired
    protected MockMvc mockMvc;

    @Configuration
    @EnableWebMvc
    @ComponentScan("com.bushemi")
    protected static class TestContextConfiguration {
        @Bean
        public MockMvc mockMvc(WebApplicationContext webApplicationContext) {
            return MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        }

        @Bean
        @Primary
        public DbConnectionService dbConnectionService() {
            return mock(DbConnectionService.class);
        }
    }
}

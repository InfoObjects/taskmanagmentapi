package com.infoobjects.taskspringapp.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.infoobjects.taskspringapp.Services.Taskdetailservice;
import com.infoobjects.taskspringapp.Services.TaskdetailserviceImpl;

@Configuration
@ComponentScan(basePackages = "com.infoobjects.taskspringapp")

public class AppConfig {
    @Bean
    public Taskdetailservice taskdetailservice() {
        return new TaskdetailserviceImpl();
    }
}

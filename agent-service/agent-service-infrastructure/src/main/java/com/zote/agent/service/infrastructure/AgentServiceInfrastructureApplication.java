package com.zote.agent.service.infrastructure;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.zote.*"})
public class AgentServiceInfrastructureApplication {

    public static void main(String[] args) {
        SpringApplication.run(AgentServiceInfrastructureApplication.class, args);
    }

}

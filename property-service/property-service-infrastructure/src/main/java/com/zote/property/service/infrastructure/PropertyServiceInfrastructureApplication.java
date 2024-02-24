package com.zote.property.service.infrastructure;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.zote.*"})
public class PropertyServiceInfrastructureApplication {

    public static void main(String[] args) {
        SpringApplication.run(PropertyServiceInfrastructureApplication.class, args);
    }

}

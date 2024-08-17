package com.zote.common.utils.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("Real estate Management API")
                        .description("API documentation for Real estate Management")
                        .version("1.0.0"));
    }

//    @Bean
//    public GroupedOpenApi userApi() {
//        return GroupedOpenApi.builder()
//                .group("User Service")
//                .pathsToMatch("/users/**")
//                .build();
//    }
}

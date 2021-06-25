package com.sber.stepanyan.compclub.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket swaggerConfiguration() {
        return new Docket(DocumentationType.SWAGGER_2)
//                .groupName("controller")
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.sber.stepanyan.compclub"))
                .paths(PathSelectors.ant("/**"))
//                .apis(RequestHandlerSelectors.any())
                .build();
    }

    private ApiInfo apiInfo()
    {
        return new ApiInfo(
                "Computer Club API",
                "API for computer club app",
                "version-1",
                "Free to use",
                new Contact("Narek Stepanyan","stepanyan.com","n@gmail.com"),
                "API License",
                "API License URL",
                Collections.emptyList());
    }
}

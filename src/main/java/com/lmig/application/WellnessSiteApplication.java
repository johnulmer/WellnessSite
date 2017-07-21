package com.lmig.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;

import static springfox.documentation.builders.PathSelectors.regex;

@SpringBootApplication
@EnableSwagger2
public class WellnessSiteApplication {

	public static void main(String[] args) {
		SpringApplication.run(WellnessSiteApplication.class, args);
		//System.out.println("Environment variable ENERGYs value is: " + System.getenv("ENERGY"));
	}
//    @Bean
//    public Docket secured() {
//        return new Docket(DocumentationType.SWAGGER_2)
//                .groupName("secured-api")
//                .select()
//                .apis(RequestHandlerSelectors.any())
//                .paths(regex("/api/*.*"))
//                .build().apiInfo(apiInfo())
//                // this will insert the required auth header filed into our apis
//                .globalOperationParameters(
//                        Arrays.asList(new ParameterBuilder()
//                                .name("x-authorization-key")
//                                .description("API Authorization Key")
//                                .modelRef(new ModelRef("string"))
//                                .parameterType("header")
//                                .required(true)
//                                .build()));
//        		
//    }

    @Bean
    public Docket unsecured() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("unsecured-api")
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(regex("/api/*.*"))
                .build().apiInfo(apiInfo());

    }

    private ApiInfo apiInfo()
    {
        ApiInfo apiInfo = new ApiInfo(
                "Wellness Site REST API",
                "GoForCode Java Engineering Final Project: Unsecured APIs",
                "version-1",
                "API TOS",
                "John Ulmer, Jeb Castelo, Dave Bell, Joe Hultz",
                "API License",
                "API License URL"
        );
        return apiInfo;
    }
}

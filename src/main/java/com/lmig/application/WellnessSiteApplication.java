package com.lmig.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@SpringBootApplication
public class WellnessSiteApplication {

	public static void main(String[] args) {
		SpringApplication.run(WellnessSiteApplication.class, args);
		System.out.println("Environment variable ENERGYs value is: " + System.getenv("ENERGY"));
	}
    @Bean
    public Docket swaggerSettings() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build().apiInfo(apiInfo())
                .pathMapping("/");
        		
    }
    private ApiInfo apiInfo()
    {
        ApiInfo apiInfo = new ApiInfo(
                "Wellness Site REST API",
                "GoForCode Java Engineering Final Project",
                "version-1",
                "API TOS",
                "John Ulmer, Jeb Castelo, Dave Bell, Joe Hultz",
                "API License",
                "API License URL"
        );
        return apiInfo;

    }
}

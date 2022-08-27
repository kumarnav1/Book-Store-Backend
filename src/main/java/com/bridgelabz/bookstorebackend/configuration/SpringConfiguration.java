package com.bridgelabz.bookstorebackend.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import static springfox.documentation.builders.PathSelectors.regex;

@Configuration
@EnableSwagger2
public class SpringConfiguration {
    @Bean
    public Docket postsApi() {
        return new Docket(DocumentationType.SWAGGER_2).groupName("public-api")
                .apiInfo(apiInfo()).select().paths(regex("/user.*")).build();
    }
    private ApiInfo apiInfo() {
        springfox.documentation.service.Contact contact = new Contact("Demo Project API", "http://brigelbaz.com", "navneetkumar42s2@gmail.com");
        return new ApiInfoBuilder().title("Demo Service Swagger API")
                .description("Demo Service Swagger API for Learning Swagger").version("0.0.1.SNAPSHOT")
                .license("Apache 2.0").licenseUrl(" http://www.apache.org/licenses/LICENSE-2.0").contact(contact)
                .build();
    }
}
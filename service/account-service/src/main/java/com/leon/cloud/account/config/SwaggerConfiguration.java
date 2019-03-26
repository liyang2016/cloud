package com.leon.cloud.account.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Created by leon on 2019/3/12.
 */

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

//    @Autowired
//    private TypeResolver typeResolver;

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.leon.cloud.account"))
                .paths(PathSelectors.any())
                .build()
                .directModelSubstitute(org.joda.time.LocalDate.class, java.sql.Date.class)
                .directModelSubstitute(org.joda.time.DateTime.class, java.util.Date.class);
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("restful api test")
                .description("cloud app")
                .termsOfServiceUrl("")
                .version("1.0")
                .build();
    }

}

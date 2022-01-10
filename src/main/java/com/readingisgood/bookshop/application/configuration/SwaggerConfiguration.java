package com.readingisgood.bookshop.application.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static com.readingisgood.bookshop.application.common.CustomHttpHeaders.*;
import static springfox.documentation.spi.DocumentationType.SWAGGER_2;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

    @Bean
    public Docket api() {
        List<Parameter> parameters = new ArrayList<>(
                Arrays.asList(
                        getCustomHeader(STOREFRONT_ID, "1"),
                        getCustomHeader(AGENT_NAME, "swagger"),
                        getCustomHeader(CORRELATION_ID, String.valueOf(UUID.randomUUID())),
                        getCustomHeader(APPLICATION_ID, "1")));

        return new Docket(SWAGGER_2)
                .useDefaultResponseMessages(false)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
                .build()
                .apiInfo(apiInfo())
                .globalOperationParameters(parameters);
    }

    private Parameter getCustomHeader(String name, String defaultValue) {
        return new ParameterBuilder()
                .name(name)
                .modelRef(new ModelRef("string"))
                .parameterType("header")
                .required(true)
                .defaultValue(defaultValue)
                .build();
    }

    private ApiInfo apiInfo() {
        String applicationName = "Book Shop Api";
        String applicationVersion = "v1";
        return new ApiInfoBuilder()
                .title(applicationName)
                .description(applicationName)
                .contact(contact())
                .version(applicationVersion)
                .build();
    }

    private Contact contact() {
        return new Contact("Gokce Apaydin", null, "gapayding@gmail.com");
    }
}
package com.yan.base.swagger;

import com.google.common.base.Predicate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.sql.Timestamp;

import static com.google.common.base.Predicates.or;
import static com.google.common.collect.Lists.newArrayList;
import static springfox.documentation.builders.PathSelectors.regex;


@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.yan"))
                .paths(paths())
                .build()
                .directModelSubstitute(Timestamp.class, String.class)
                .useDefaultResponseMessages(false)
                .globalResponseMessage(RequestMethod.GET,
                        newArrayList(new ResponseMessageBuilder()
                                .code(500)
                                .message("服务器异常")
                                .build()))
                .globalResponseMessage(RequestMethod.POST,
                        newArrayList(new ResponseMessageBuilder()
                                .code(500)
                                .message("服务器异常")
                                .build()))
                .globalResponseMessage(RequestMethod.PUT,
                        newArrayList(new ResponseMessageBuilder()
                                .code(500)
                                .message("服务器异常")
                                .build()))
                .globalResponseMessage(RequestMethod.DELETE,
                        newArrayList(new ResponseMessageBuilder()
                                .code(500)
                                .message("服务器异常")
                                .build()));
    }

    private Predicate<String> paths() {
        return or(
                regex("/api/v1/.*"),
                regex("/manager/v1/.*"));
    }


    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("RESTFUL APIs 接口文档")
                .contact(contact())
                .version("1.0")
                .build();
    }

    private Contact contact() {
        return new Contact("后端开发团队", "", "admin@shangweiec.com");
    }

}

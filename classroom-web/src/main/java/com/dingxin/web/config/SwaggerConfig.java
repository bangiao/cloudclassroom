package com.dingxin.web.config;

import com.dingxin.common.annotation.ManTag;
import com.dingxin.common.annotation.UserTag;
import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@Profile({"dev","test"})
@Configuration
@EnableSwagger2
@EnableKnife4j
@Import(BeanValidatorPluginsConfiguration.class)
public class SwaggerConfig {


    @Bean(value = "manApi")
    public Docket manApi() {
        Docket docket=new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                //分组名称
                .groupName("管理端")
                .select()
                //这里指定Controller扫描包路径
//                .apis(RequestHandlerSelectors.basePackage("com.dingxin.web.controller"))
                .apis(RequestHandlerSelectors.withClassAnnotation(ManTag.class))
                .paths(PathSelectors.any())
                .build();
        return docket;
    }

    @Bean(value = "userApi")
    public Docket userApi() {
        Docket docket=new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                //分组名称
                .groupName("用户端")
                .select()
                //这里指定Controller扫描包路径
//                .apis(RequestHandlerSelectors.basePackage("com.dingxin.web.controller"))
                .apis(RequestHandlerSelectors.withClassAnnotation(UserTag.class))
                .paths(PathSelectors.any())
                .build();
        return docket;
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("云课堂系统")
                .description("本系统的建设，可在一定程度上提高教育资源匮乏地区的教学质量和优质教育资源的共享，增强对各类资源合理整合与集成、加大本校班与班之间的互动交流。实现线下与线上相结合的远程课堂模式。")
                .termsOfServiceUrl("http://www.szdxsoft.com")
                .contact("Apache")
                .license("南科大网络建设许可证")
                .licenseUrl("http://www.szdxsoft.com")
                .version("1.0")
                .build();
    }

}

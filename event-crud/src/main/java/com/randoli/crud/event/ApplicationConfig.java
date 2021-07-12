package com.randoli.crud.event;

import org.apache.camel.opentracing.starter.CamelOpenTracing;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import springfox.documentation.spring.data.rest.configuration.SpringDataRestConfiguration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@CamelOpenTracing
@EnableSwagger2
@Import(SpringDataRestConfiguration.class)
public class ApplicationConfig {

}

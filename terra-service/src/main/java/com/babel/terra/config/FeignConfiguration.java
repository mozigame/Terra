package com.babel.terra.config;


import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(basePackages = "com.babel.terra")
public class FeignConfiguration {

}
